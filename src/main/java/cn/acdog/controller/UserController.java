package cn.acdog.controller;

import cn.acdog.pojo.Result;
import cn.acdog.pojo.User;
import cn.acdog.service.UserService;
import cn.acdog.utils.JwtUtil;
import cn.acdog.utils.Md5Util;
import cn.acdog.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")//注册
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password, Integer role) {
//        log.info("username:{}",username);
        //查询用户名是否存在
        User u = userService.findByUserName(username);
        if (u == null) {
            userService.register(username, password, role);//注册用户
            return Result.success();
        } else {
            return Result.error("用户名已被专用");
        }
    }

    @PostMapping("/login")//登录
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //查找用户名
        User loginUser = userService.findByUserName(username);
        if (loginUser == null) {
            return Result.error("用户名不存在");
        } else {
            String md5Password = Md5Util.getMD5String(password);
            if (loginUser.getPassword().equals(md5Password)) {
                Map<String,Object> claims = new HashMap<>();
                //把id，用户名，角色存储token
                claims.put("id",loginUser.getId());
                claims.put("username",loginUser.getUsername());
                claims.put("role",loginUser.getRole());
                String token = JwtUtil.genToken(claims);
                return Result.success(token);
            } else {
                return Result.error("密码错误");
            }
        }
    }
    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name="Authorization") String token*/){
        /*Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");*/
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user =  userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updatePassword")
    public Result updatePassword(@RequestBody Map<String,String> params){
        //校验参数
        String oldPwd =  params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if(!StringUtils.hasLength(oldPwd)|| !StringUtils.hasLength(newPwd)||!StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要的参数");
        }
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        if(loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码填写不正确");
        }
        if(rePwd.equals(newPwd)){
            return Result.error("两次填写的新密码不一样");
        }
        Integer id = (Integer) map.get("id");
        userService.updatePwd(newPwd,id);
        return Result.success();

        //调用service更新密码
    }
}
