package cn.acdog.controller;

import cn.acdog.pojo.PageBean;
import cn.acdog.pojo.PostJob;
import cn.acdog.pojo.Result;
import cn.acdog.pojo.User;
import cn.acdog.service.PostJobService;
import cn.acdog.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/postJob")
@Validated
public class PostJobController {
    @Autowired
    private PostJobService postJobService;

    @PostMapping("/addPostJob")//添加招聘信息
    public Result add(@RequestBody PostJob postJob){
        log.info("添加招聘信息，参数为：postJob={}",postJob);
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        postJobService.addPostJob(postJob,id);
        return Result.success();
    }
    @DeleteMapping("/deletePostJob")//删除招聘信息
    public Result delete(Integer id){
        log.info("删除招聘信息，参数为：id={}",id);
        postJobService.deletePostJob(id);
        return Result.success();
    }

    @PostMapping("/updatePostJob")//修改招聘信息
    public Result update(@RequestBody PostJob postJob){
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        log.info("用户={}修改招聘信息，参数为：postJob={}",userId,postJob);
        postJobService.updatePostJob(postJob);
        return Result.success();
    }

    @GetMapping("/getPostJob")//获取招聘信息
    public Result<PageBean<PostJob>> getJobList(Integer pageNum,Integer pageSize,
                                                @RequestParam(required = false)Integer userId,
                                                @RequestParam(required = false)String fuzzySearch,
                                                @RequestParam(required = false)Integer state){

        log.info("分页查询，参数为：page={},pageSize={},userId={},fuzzySearch={},state={}",pageNum,pageSize,userId,fuzzySearch,state);
        PageBean<PostJob> getJobList = postJobService.getJobList(pageNum,pageSize,userId,fuzzySearch,state);
        return Result.success(getJobList);
    }

}
