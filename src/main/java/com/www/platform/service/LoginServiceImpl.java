package com.www.platform.service;

import com.www.platform.dao.UserMapper;
import com.www.platform.entity.User;
import com.www.platform.util.Crypt;
import com.www.platform.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by upsmart on 17-8-4.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午2:32
 */
@Service
public class LoginServiceImpl implements LoginService{
@Autowired private UserMapper userMapper;


    @Override public String login(Map<String, Object> map) {
        String result="";
        User user=new User();
        String uname=map.get("uname").toString();
        String upwd=map.get("upwd").toString();
        user.setUname(uname);
        try {
            user=userMapper.selectByUname(uname);
            if(user.getUpwd().equals(Md5.MD5(upwd))){
                result="success";
            }
        }catch(NullPointerException e){
            e.printStackTrace();
            result=null;

        }

        return result;
    }

    @Override public String register(Map<String, Object> map) {
        String result = "";
        Integer a=0;
        User user=new User();
        user.setUname((String) map.get("uname"));
        user.setUemail((String) map.get("uemail"));
        user.setUpwd((String)map.get("upwd"));
        user.setCreatetime(new Date());
        user.setModtime(new Date());
        if(!(user.getUpwd().equals(Md5.MD5((String) map.get("upwdconfirm"))))) {
            result = "两次输入密码不一样";
        }else{
        try {
            a = this.userMapper.insert(user);
            if (a==1) {
                result = "新建成功";
            }
        }  catch (Exception e){
            e.printStackTrace();
            result = "新建失败";
        }
        }
        return result;
    }

    @Override public String editPassword(Map<String, Object> map) {
        String result = "0";
        User user = new User();
        user.setUname((String) map.get("name"));
        try {
            user = this.userMapper.selectByUname(user.getUname());
            if (user != null) {
                if (! map.get("unewpassword").equals(map.get("uconfnewpwd"))) {
                      if (Md5.MD5((String) map.get("uoldpassword")).equals(user.getUpwd())) {
                          result="密码错误";

                        return result;
                    }else {
                        user.setUpwd(Md5.MD5((String) map.get("unewpassword")));
                    }
                } else {
                    result = "两次输入密码不一样";

                    return result;
                }
            }

            this.userMapper.updateUpwd(user.getUname(),new Date());
        } catch (NullPointerException e) {
            result = "修改失败";
            e.printStackTrace();
        }
        return result;
    }



}
