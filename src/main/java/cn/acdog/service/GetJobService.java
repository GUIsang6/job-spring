package cn.acdog.service;
import cn.acdog.pojo.GetJob;
import cn.acdog.pojo.PageBean;
public interface GetJobService {
    //添加求职信息
    void addGetJob(GetJob getJob,Integer id);
    //删除求职信息
    void deleteGetJob(Integer id);
    //获取求职信息列表
    PageBean<GetJob> getJobList(Integer page, Integer pageSize, Integer userId, String fuzzySearch);
    //修改求职信息
    void updateGetJob(GetJob getJob);

    GetJob findGetJobById(Integer id);
}
