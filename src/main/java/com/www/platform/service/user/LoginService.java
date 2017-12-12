package com.www.platform.service.user;


import com.www.platform.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by upsmart on 17-11-13.
 *
 * @author zjl
 * @version 0.0
 */
public interface LoginService {
//   String login(Map<String,Object> map);
    User login(Map<String,Object> map);
  String register(Map<String ,Object> map);
   String editPassword(Map<String, Object> map);
//    String editInfo(Map<String, Object> map);
    String editRoleAndEmail(Map<String, Object> map);
    List<User> selectAllUser();
    List<User> selectLikeUname(Map<String, Object> map);
    String  deleteUser(Map<String, Object> map);
    User selectUserByUname(Map<String, Object> map);
    List<User> selectUserByRole(Map<String, Object> map);
}
