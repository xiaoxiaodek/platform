package com.www.platform.service;

import com.www.platform.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by upsmart on 17-11-13.
 *
 * @author zjl
 * @version 0.0
 */
public interface LoginService {
   String login(Map<String,Object> map);
  String register(Map<String ,Object> map);
   String editPassword(Map<String, Object> map);
}
