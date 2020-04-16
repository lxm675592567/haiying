package haiying.service.physical.mapper;

import haiying.service.physical.domain.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from user where id=#{id}")
    User findById(long id);
}
