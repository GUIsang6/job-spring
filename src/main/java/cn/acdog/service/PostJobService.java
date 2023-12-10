package cn.acdog.service;

import cn.acdog.pojo.PageBean;
import cn.acdog.pojo.PostJob;

public interface PostJobService {
    //添加招聘信息
    void addPostJob(PostJob postJob, Integer id);
    //删除招聘信息
    void deletePostJob(Integer id);
    //修改招聘信息
    void updatePostJob(PostJob postJob);

    PageBean<PostJob> getJobList(Integer pageNum, Integer pageSize, Integer userId, String fuzzySearch, Integer state);
}
