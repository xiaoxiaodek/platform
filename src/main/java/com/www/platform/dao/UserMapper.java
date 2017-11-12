package com.www.platform.dao;

import com.www.platform.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);
    User selectByUname(String uname);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}