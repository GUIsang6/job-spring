package cn.acdog.controller;

import cn.acdog.pojo.GetJob;
import cn.acdog.pojo.PageBean;
import cn.acdog.pojo.Result;
import cn.acdog.service.GetJobService;
import cn.acdog.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/getJob")
@Validated
public class GetJobController {
    @Autowired
    private GetJobService getJobService;
    @PostMapping("/addGetJob")//添加求职信息
    public Result add(@RequestBody GetJob getJob){
        log.info("添加求职信息，参数为：getJob={}",getJob);
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        getJobService.addGetJob(getJob,id);
        return Result.success();
    }
    @DeleteMapping("/deleteGetJob")//
    // 删除求职信息
    public Result delete(Integer id){
        log.info("删除求职信息，参数为：id={}",id);
        getJobService.deleteGetJob(id);
        return Result.success();
    }
    @GetMapping("/getJobList")//获取求职信息列表
    public Result<PageBean<GetJob>> getJobList(Integer pageNum,Integer pageSize,
                             @RequestParam(required = false) Integer userId,
                             @RequestParam(required = false) String fuzzySearch,
                                               @RequestParam(required = false) Integer state){
        log.info("求职信息分页查询，参数为：page={},pageSize={},userId={},fuzzySearch={},state: {}",pageNum,pageSize,userId,fuzzySearch,state);
        PageBean<GetJob> getJobList = getJobService.getJobList(pageNum, pageSize, userId, fuzzySearch,state);
        return Result.success(getJobList);
    }
    @PostMapping("/updateGetJob")//修改求职信息
    public Result update(@RequestBody GetJob getJob){
        log.info("修改求职信息，参数为：getJob={}",getJob);
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        GetJob originG = getJobService.findGetJobById(getJob.getId());
        if(Objects.equals(originG.getCreateUser(), userId)) {
            getJobService.updateGetJob(getJob);
            return Result.success();
        }else{
            return Result.error("没权限");
        }

    }
}
