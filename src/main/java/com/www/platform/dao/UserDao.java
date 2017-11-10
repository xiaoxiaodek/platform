package com.www.platform.dao;

import com.www.platform.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by upsmart on 17-7-21.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午4:45
 */

public interface UserDao {
    List<User> findByAll();
    User findByName(@Param("username") String username);
    void add();
    void delete(@Param("user_id") int user_id);
}