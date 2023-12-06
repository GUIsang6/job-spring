package cn.acdog.mapping;

import cn.acdog.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    //根据用户名查询用户
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);
    //注册用户"
    @Insert("insert into user(username,password,create_time,update_time,nickname,role,email,avatar,vip,status)"+
            " values(#{username},#{password},now(),now(),#{username},#{role},null,null,0,1)")
    void add(String username, String password, Integer role);

    @Update("update  user set username=#{username},nickname=#{nickname},email=#{email},avatar=#{avatar},vip=#{vip},status=#{status},role=#{role},update_time=now() where id=#{id}")
    void update(User user);

    @Update("update user set password=#{md5String},update_time=now() where id=#{id}")
    void updatePwd(String md5String,Integer id);
}
