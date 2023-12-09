package cn.acdog.mapper;

import cn.acdog.pojo.GetJob;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GetJobMapper {
    //添加求职信息
    @Insert("insert into get_job( job, address, pay_min, pay_max, job_category,create_user, `condition`, state, create_time,updata_time) " +
            "values( #{getJob.job}, #{getJob.address}, #{getJob.payMin}, #{getJob.payMax}, #{getJob.jobCategory},#{id}, #{getJob.condition}, 2, now(), now())")
    void addGetJob(GetJob getJob,Integer id);
    //删除求职信息
    @Delete("delete from get_job where id=#{id}")
    void deleteGetJob(Integer id);

    //查询求职信息列表
    List<GetJob> getJobList(Integer userId, String fuzzySearch);

    //修改求职信息
    @Update("update get_job set job=#{job}, address=#{address}, " +
            "pay_min=#{payMin}, pay_max=#{payMax}, job_category=#{jobCategory}," +
            " `condition`=#{condition}, updata_time=now(),state=#{state} where id=#{id}")
    void updateGetJob(GetJob getJob);



    @Select("select * from get_job where id = #{id}")
    GetJob findGetJobById(Integer id);
}