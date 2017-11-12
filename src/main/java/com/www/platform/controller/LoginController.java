package com.www.platform.controller;

import com.www.platform.dao.UserMapper;
import com.www.platform.service.GenerateTokens;
import com.www.platform.service.LoginService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    private static Logger logger= LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;


    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody Map<String,String> map, HttpSession session){
        String username=map.get("uname");
        String password=map.get("upwd");
        session.setAttribute();
        try{
            if(null==this.loginService.login(map)){

            }else{}
        }catch (){}


    }


    @RequestMapping(value = "/preview",method = RequestMethod.GET)
    @ResponseBody
    public void perview(HttpServletRequest req) throws Exception{
       Cookie[] cookies = req.getCookies();
       Claims a= GenerateTokens.parseJWT(cookies[0].getValue());
       String id=a.getSubject();
    }
}
