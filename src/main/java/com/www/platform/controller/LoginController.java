package com.www.platform.controller;

import com.www.platform.dao.UserDao;
import com.www.platform.service.GenerateTokens;
import com.www.platform.service.LoginService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by upsmart on 17-8-7.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午6:00
 */
@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    private UserDao userdao;
    @Autowired
    private LoginService loginService;


    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody Map<String,String> map, HttpServletResponse res){
        String username=map.get("uname");
        String password=map.get("upassword");

        boolean a=false;
        try{
            a=loginService.validateUser(username,password);
        }catch (Exception e){
                e.printStackTrace();
                return "验证出错";
            }

        if(a){
            String JWT=loginService.JWT(username);
            Cookie cookie =new Cookie("JWT",JWT);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            res.addCookie(cookie);

            return "授权成功";
        }else{
            return "密码验证不通过";
        }
    }


    @RequestMapping(value = "/preview",method = RequestMethod.GET)
    @ResponseBody
    public void perview(HttpServletRequest req) throws Exception{
       Cookie[] cookies = req.getCookies();
       Claims a= GenerateTokens.parseJWT(cookies[0].getValue());
       String id=a.getSubject();
    }
}
