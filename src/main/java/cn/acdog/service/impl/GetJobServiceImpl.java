package cn.acdog.service.impl;

import cn.acdog.mapper.GetJobMapper;
import cn.acdog.pojo.GetJob;
import cn.acdog.pojo.PageBean;
import cn.acdog.service.GetJobService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetJobServiceImpl implements GetJobService {

    @Autowired
    private GetJobMapper getJobMapper;

    @Override
    public void addGetJob(GetJob getJob, Integer id) {
        getJobMapper.addGetJob(getJob,id);
    }

    @Override
    public void deleteGetJob(Integer id) {
        getJobMapper.deleteGetJob(id);
    }

    @Override
    public PageBean<GetJob> getJobList(Integer pageNum, Integer pageSize, Integer userId, String fuzzySearch,Integer state){
        //创建PageBean对象
        PageBean<GetJob> pageBean = new PageBean<>();
        //开启分页查询
        PageHelper.startPage(pageNum,pageSize);
        //调用mapper查询
        List<GetJob> getJobList = getJobMapper.getJobList(userId, fuzzySearch,state);
        Page<GetJob> page = (Page<GetJob>) getJobList;

        //封装数据
        pageBean.setTotal(page.getTotal());
        pageBean.setItems(page.getResult());
        return pageBean;
    }

    @Override
    public void updateGetJob(GetJob getJob) {
        getJobMapper.updateGetJob(getJob);
    }

    @Override
    public GetJob findGetJobById(Integer id) {
        return getJobMapper.findGetJobById(id);
    }


}
