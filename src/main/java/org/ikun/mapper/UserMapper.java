package org.ikun.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ikun.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
