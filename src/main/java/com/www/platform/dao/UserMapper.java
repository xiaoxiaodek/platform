package com.www.platform.dao;

import com.www.platform.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);

    User selectByUname(String uname);

    List<User> selectByRole(Integer role);

    List<User> selectAllUser();

    List<User> selectLikeUname(@Param("uname") String uname);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int     updateUpwd(@Param("modtime") Date modtime, @Param("upwd") String upwd, @Param("uname") String uname);

//    int     updateInfo(@Param("modtime") Date modtime, @Param("uemail") String uemail, @Param("uname") String uname);

    int     updateRoleAndEmail(@Param("uemail") String uemail,@Param("modtime") Date modtime, @Param("role") Integer role, @Param("uname") String uname);
}