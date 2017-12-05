package com.www.platform.service.user;


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
    String editInfo(Map<String, Object> map);
    String editRole(Map<String, Object> map);
}
