package cn.acdog.service.impl;

import cn.acdog.mapper.AdminMapper;
import cn.acdog.mapper.UserMapper;
import cn.acdog.pojo.PageBean;
import cn.acdog.pojo.User;
import cn.acdog.service.AdminService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public PageBean<User> getUser(Integer pageNum, Integer pageSize, String fuzzySearch, Integer role) {
        //创建PageBean对象
        PageBean<User> pageBean = new PageBean<>();
        //开启分页查询
        PageHelper.startPage(pageNum,pageSize);
        //调用mapper查询
        List<User> userList = adminMapper.getUser(fuzzySearch, role);
        Page<User> page = (Page<User>) userList;
        //封装数据
        pageBean.setTotal(page.getTotal());
        pageBean.setItems(page.getResult());
        return pageBean;
    }
}
