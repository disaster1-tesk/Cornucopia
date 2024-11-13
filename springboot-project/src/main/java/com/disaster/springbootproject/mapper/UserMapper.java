package com.disaster.springbootproject.mapper;

import com.disaster.springbootproject.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findOne(String username);
}
