package cn.acdog.service;

import cn.acdog.pojo.PageBean;
import cn.acdog.pojo.User;

public interface AdminService {
    PageBean<User> getUser(Integer pageNum, Integer pageSize, String fuzzySearch, Integer role);
}
