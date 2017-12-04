package com.www.platform.dao;

import com.www.platform.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);

    User selectByUname(String uname);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int     updateUpwd(@Param("modtime") Date modtime, @Param("upwd") String upwd, @Param("uname") String uname);

    int     updateInfo(@Param("modtime") Date modtime, @Param("uemail") String uemail, @Param("uname") String uname);

    int     updateRole(@Param("modtime") Date modtime, @Param("role") String role, @Param("uname") String uname);
}