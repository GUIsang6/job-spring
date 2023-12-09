package cn.acdog.mapper;

import cn.acdog.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<User> getUser(String fuzzySearch, Integer role);
}
