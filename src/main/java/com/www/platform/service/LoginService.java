package com.www.platform.service;

import com.www.platform.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by upsmart on 17-8-4.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午2:31
 */
public interface LoginService {
    List<User> findByAll();
    void add();
    void delete(@Param("user_id") int user_id);
    boolean validateUser(String username,String password);
    String JWT(@Param("username")String username);
}
