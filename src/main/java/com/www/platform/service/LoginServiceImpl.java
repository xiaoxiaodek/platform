package com.www.platform.service;

import com.www.platform.dao.UserDao;
import com.www.platform.entity.User;
import com.www.platform.util.Crypt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private UserDao userdao;
    @Override
    public List<User> findByAll(){
       return userdao.findByAll();
    }
    @Override
    public  void delete(int user_id ){
    }
    @Override
    public boolean validateUser(String username,String password) {
        String pwd=Crypt.decodeBase64(password);
        User xiaoming;
        try{
            xiaoming=userdao.findByName(username);
            if(xiaoming.getPassword().equals(pwd)){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public String JWT(String username) {
        User a=userdao.findByName(username);
        String JWT="";
        try {
            JWT=GenerateTokens.createJWT("1","Online JWT Builder",username,1800000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return JWT;
    }

    @Override
    public  void add(){}

}
