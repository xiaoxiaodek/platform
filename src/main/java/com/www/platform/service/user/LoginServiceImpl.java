package com.www.platform.service.user;

import com.www.platform.dao.UserMapper;
import com.www.platform.entity.User;
import com.www.platform.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by upsmart on 17-11-12.
 *
 * @author zjl
 * @version 0.0
 */
@Service public class LoginServiceImpl implements LoginService {
    @Autowired private UserMapper userMapper;

    @Override public User login(Map<String, Object> map) {
        String result = "";
        User user = new User();
        String uid=null;
        String uname = map.get("uname").toString();
        String upwd = map.get("upwd").toString();
        user.setUname(uname);
        try {
            user = userMapper.selectByUname(uname);

//            uid=user.getUid().toString();
            if (user.getUpwd().equals(Md5.MD5(upwd))) {
                return user;
//                result = uid;
            }else{
                user=null;
                return user;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            user=null;
            return user;
        }
//        return user;
    }

    @Override public String register(Map<String, Object> map) {
        String result = "";
        Integer a = 0;
        User user = new User();
        User user1 = new User();
        String uname = (String) map.get("uname");
        String uemail = (String) map.get("uemail");
        String upwd = (String) map.get("upwd");
        String role = (String) map.get("role");
        System.out.println("uemail++++++"+uemail+"======");
        String upwdconfirm = (String) map.get("upwdconfirm");
        if (null != uname && uname.indexOf(" ") == -1 && null != uemail && uemail.indexOf(" ") == -1
            && null != upwd && upwd.indexOf(" ") == -1 && null != upwdconfirm
            && upwdconfirm.indexOf(" ") == -1&&null != role && role.indexOf(" ") == -1) {

            user1 = userMapper.selectByUname(uname);

            if (user1 != null) {
                result = "用户名已存在";
                return result;
            } else {
                user.setUname((String) map.get("uname"));
                user.setUemail((String) map.get("uemail"));
                user.setUpwd(Md5.MD5((String) map.get("upwd")));
                user.setRole(Integer.parseInt(map.get("role").toString()));
                user.setCreatetime(new Date());
                user.setModtime(new Date());

                if (!(user.getUpwd().equals(Md5.MD5((String) map.get("upwdconfirm"))))) {
                    result = "两次输入密码不一样!!!!";
                } else {
                    try {
                        a = this.userMapper.insert(user);
                        if (a == 1) {
                            result = "新建成功";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        result = "新建失败";
                        return result;
                    }
                }
            }
        } else {
            return "表单不完整";

        }
        return result;
    }

    @Override public String editPassword(Map<String, Object> map) {
        String result = "0";
        int a = 21;
        User user = new User();
        user.setUname((String) map.get("uname"));
        try {
            user = this.userMapper.selectByUname(user.getUname());

            if (user != null) {
                if (map.get("unewpassword").equals(map.get("uconfnewpwd"))) {
                    if (Md5.MD5((String) map.get("uoldpassword")).equals(user.getUpwd())) {
                        user.setUpwd(Md5.MD5((String) map.get("unewpassword")));
                        String uname = user.getUname();
                        String upwd = user.getUpwd();
                        Date modtime = new Date();
                        a = this.userMapper.updateUpwd(modtime, upwd, uname);
                        if (a == 1) {
                            result = "修改成功";
                        } else {
                            result = "修改失败";
                            return result;
                        }
                    } else {
                        result = "密码错误";
                        return result;
                    }
                } else {
                    result = "两次输入密码不一样";
                    return result;
                }
            }
        } catch (NullPointerException e) {
            result = "修改失败";
            e.printStackTrace();
            return result;
        }
        return result;
    }

    @Override public String editInfo(Map<String, Object> map) {
        String result = "0";
        int a = 21;
        User user = new User();
        user.setUname((String) map.get("uname"));
        user=this.userMapper.selectByUname(user.getUname());
        if(user!=null){
            user.setUemail(map.get("email").toString());
            String uname = user.getUname();
            String uemail = user.getUemail();
            Date modtime = new Date();
            a=this.userMapper.updateInfo(modtime, uemail, uname);
            if(a==1){
                result="修改成功";
            }else{
                result="修改失败";
                return result;
            }

        }

        return result;

    }

    @Override public String editRole(Map<String, Object> map) {

        String result = "0";
        int a = 21;
        User user = new User();
        user.setUname((String) map.get("uname"));
        user=this.userMapper.selectByUname(user.getUname());
        if(user!=null){
            user.setRole(Integer.parseInt(map.get("role").toString()));
            String uname = user.getUname();
            int role = user.getRole();
            Date modtime = new Date();
            a=this.userMapper.updateRole(modtime, role, uname);
            if(a==1){
                result="修改成功";
            }else{
                result="修改失败";
                return result;
            }

        }

        return result;
    }

    @Override public List<User> selectAllUser() {
        List<User> userList=new ArrayList<User>();
        try {
            userList=  this.userMapper.selectAllUser();
        }catch (NullPointerException e){
            e.printStackTrace();
            userList=null;
        }


        return userList;
    }

    @Override public String deleteUser(Map<String, Object> map) {

        String result=null;
        User user=new User();
        int uid=Integer.parseInt(map.get("uid").toString());
        try {
            this.userMapper.deleteByPrimaryKey(uid);
            result="删除成功";
        }catch(Exception e){
            result="删除失败";
            e.printStackTrace();
            return  result;
        }

        return result;




    }

    @Override public User selectUserByUname(Map<String, Object> map) {
        User user=new User();

        try {
            String uname=map.get("uname").toString();
            user=  this.userMapper.selectByUname( uname);
        }catch (NullPointerException e){
            e.printStackTrace();
            user=null;
        }

        return user;
    }



}
