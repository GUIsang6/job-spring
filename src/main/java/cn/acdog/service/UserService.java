package cn.acdog.service;

import cn.acdog.pojo.User;

public interface UserService {
    //根据用户名查询用户
    User findByUserName(String username);
    //注册用户
    void register(String username, String password, Integer role);

    //更新用户信息
    void update(User user);

    //更新密码
    void updatePwd(String newPwd,Integer id);

    //删除用户
    void delete(Integer id);

}
