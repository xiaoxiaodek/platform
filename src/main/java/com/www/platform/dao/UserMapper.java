package com.www.platform.dao;

import com.www.platform.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);
    User selectByUname(String uname);
    int updateByPrimaryKeySelective(User record);
      int     updateUpwd(String uname,Date date);
    int updateByPrimaryKey(User record);
}