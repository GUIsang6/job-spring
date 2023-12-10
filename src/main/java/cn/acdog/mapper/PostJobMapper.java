package cn.acdog.mapper;

import cn.acdog.pojo.PostJob;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Mapper
public interface PostJobMapper {
    //添加招聘信息
   @Insert("insert into post_job(job, company, address, pay_min, pay_max, `condition`, job_category, create_user, img, info, state, create_time, updata_time) " +
           "VALUE (#{job}, #{company}, #{address}, #{payMin}, #{payMax}, #{condition}, #{jobCategory}, #{createUser}, #{img}, #{info}, 2, now(), now())")
    void addPostJob(PostJob postJob);
    //删除招聘信息
   @Delete("delete from post_job where id=#{id}")
    void deletePostJob(Integer id);
    //修改招聘信息
   @Update("update post_job set job=#{job}, company=#{company}, address=#{address}, pay_min=#{payMin}, pay_max=#{payMax}, `condition`=#{condition}, job_category=#{jobCategory}, create_user=#{createUser}, img=#{img}, info=#{info}, state=#{state}, updata_time=now() where id=#{id}")
    void updatePostJob(PostJob postJob);
    //获取招聘信息

    List<PostJob> getJobList(Integer userId, String fuzzySearch,Integer state);
}
