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

    @PostMapping("/register")//用户注册
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password, Integer role) {
        log.info("注册用户，参数为：username={},password={},role={}", username, password, role);
        //查询用户名是否存在
        User u = userService.findByUserName(username);
        if (u == null) {
            userService.register(username, password, role);//注册用户
            User loginUser = userService.findByUserName(username);
            Map<String,Object> claims = new HashMap<>();
            //把id，用户名，角色存储token
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            claims.put("role",loginUser.getRole());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        } else {
            return Result.error("用户名已被专用");
        }
    }

    @PostMapping("/login")//用户登录
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        log.info("用户登录，参数为：username={},password={}", username, password);
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

    @GetMapping("/userInfo")//查询用户信息
    public Result<User> userInfo(/*@RequestHeader(name="Authorization") String token*/){
        /*Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");*/
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user =  userService.findByUserName(username);
        log.info("查询用户信息，参数为：user={}",user);
        return Result.success(user);
    }


    @PostMapping("/update")//更新用户信息
    public Result update(@RequestBody @Validated User user){
        log.info("更新用户信息，参数为：user={}",user);
        userService.update(user);
        return Result.success(user);
    }

    @PatchMapping("/updatePassword")//更新密码
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
        if(!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            log.info("原密码错误，更新密码失败");
            return Result.error("原密码错误");

        }
        if(!newPwd.equals(rePwd)){
            log.info("两次密码不一致，更新密码失败");
            return Result.error("两次密码不一致");
        }
        //调用service更新密码
        Integer id = (Integer) map.get("id");
        userService.updatePwd(newPwd,id);
        log.info("更新密码成功，参数为：params={}",params);
        return Result.success();

    }
}
