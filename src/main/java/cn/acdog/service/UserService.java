package cn.acdog.service;

import cn.acdog.pojo.User;

public interface UserService {
    //根据用户名查询用户
    User findByUserName(String username);
    //注册用户
    void register(String username, String password, Integer role);

    //更新
    void update(User user);

    void updatePwd(String newPwd,Integer id);
}
