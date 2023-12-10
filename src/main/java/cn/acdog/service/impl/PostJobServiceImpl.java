package cn.acdog.service.impl;

import cn.acdog.mapper.PostJobMapper;
import cn.acdog.pojo.PageBean;
import cn.acdog.pojo.PostJob;
import cn.acdog.service.PostJobService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostJobServiceImpl implements PostJobService {
    @Autowired
    private PostJobMapper postJobMapper;

    //添加招聘信息
    @Override
    public void addPostJob(PostJob postJob, Integer id) {
        postJob.setCreateUser(id);
        postJobMapper.addPostJob(postJob);
    }
    //删除招聘信息
    @Override
    public void deletePostJob(Integer id) {
        postJobMapper.deletePostJob(id);
    }
    //修改招聘信息
    @Override
    public void updatePostJob(PostJob postJob) {
        postJobMapper.updatePostJob(postJob);
    }

    //获取招聘信息
    @Override
    public PageBean<PostJob> getJobList(Integer pageNum, Integer pageSize, Integer userId, String fuzzySearch,Integer state) {
       //创建pageBean对象
        PageBean<PostJob> pageBean = new PageBean<>();
        //开启分页查询
        PageHelper.startPage(pageNum,pageSize);
        //调用mapper查询
        List<PostJob> postJobList = postJobMapper.getJobList(userId, fuzzySearch,state);
        Page<PostJob> page = (Page<PostJob>) postJobList;

        //封装数据
        pageBean.setTotal(page.getTotal());
        pageBean.setItems(page.getResult());
        return pageBean;
    }

}
