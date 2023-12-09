package cn.acdog.controller;

import cn.acdog.pojo.*;
import cn.acdog.service.AdminService;
import cn.acdog.service.GetJobService;
import cn.acdog.service.PostJobService;
import cn.acdog.service.UserService;
import cn.acdog.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("admin")
@Validated
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private GetJobService getJobService;
    @Autowired
    private PostJobService postJobService;
    //添加用户及修改永华
    @PostMapping("/addUser")
    public Result addUser(@RequestBody Map<String,String> map){
        log.info("添加用户，参数为：map={}",map);
        String username = map.get("username");
        String password = "123456";
        Integer role = Integer.valueOf(map.get("role"));
        String email = map.get("email");
        //查询用户名是否存在
        User u = userService.findByUserName(username);
        if (u == null) {
            // 如果用户不存在，添加用户
            log.info("注册用户，参数为：username={},password={},role={}", username, password, role);
            userService.register(username, password, role);
            return Result.success("管理员新增用户成功");
        } else {
            // 如果用户存在，修改用户信息
            log.info("修改用户，参数为：username={},role={}", username, role);
            u.setUsername(username);
            u.setRole(role);
            userService.update(u);
            return Result.success("修改用户={}信息");
        }

    }
    //查询用户
    @GetMapping("/getUser")
    public Result<PageBean<User>> getUser(Integer pageNum, Integer pageSize,
                                          @RequestParam(required = false) String fuzzySearch,
                                          @RequestParam(required = false) Integer role){
        log.info("用户列表分页查询，参数为：page={},pageSize={},fuzzySearch={}",pageNum,pageSize,fuzzySearch);
        PageBean<User> userList = adminService.getUser(pageNum, pageSize, fuzzySearch, role);
        return Result.success(userList);
    }
    //删除用户
    @DeleteMapping("/deleteUser")
    public Result deleteUser(@RequestParam Integer userId){
        log.info("删除用户，参数为：userId={}",userId);
        userService.delete(userId);
        return Result.success("删除用户");
    }
    //修改用户信息
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user){
        log.info("修改用户信息，参数为：user={}",user);
        userService.update(user);
        return Result.success("修改用户信息");
    }

    //审核求职信息
    @PostMapping("/checkGetJob")
    public Result checkGetJob(@RequestBody GetJob getJob){
        log.info("审核求职信息：getJob={}",getJob);
        getJobService.updateGetJob(getJob);
        return Result.success("求职已审核");
    }
    //审核招聘信息
    @PostMapping("/checkPostJob")
    public Result checkPost(@RequestBody PostJob postJob){
        log.info("审核招聘信息：postJob={}",postJob);
        postJobService.updatePostJob(postJob);
        return  Result.success("招聘已审核");
    }

}
