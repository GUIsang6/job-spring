package cn.acdog.service.impl;

import cn.acdog.mapper.UserMapper;
import cn.acdog.pojo.User;
import cn.acdog.service.UserService;
import cn.acdog.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;//注入UserMapper
    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public void register(String username, String password, Integer role) {
    //加密
        String md5Password = Md5Util.getMD5String(password);
        userMapper.add(username,md5Password,role);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void updatePwd(String newPwd , Integer id) {
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }

    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }


}
