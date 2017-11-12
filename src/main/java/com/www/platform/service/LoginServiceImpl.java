package com.www.platform.service;

import com.www.platform.dao.UserMapper;
import com.www.platform.entity.User;
import com.www.platform.util.Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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


    @Override public String login(Map<String, Object> map) {
        User user=new User();
        user.setUname(map.get("uname").toString());
        




        return null;
    }
}
