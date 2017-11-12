package com.www.platform.controller;

import com.www.platform.constant.GlobalConstants;

import com.www.platform.message.BaseMessage;
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
 * @Author zjl

 */
@Controller
@RequestMapping("login")
public class LoginController {
    private static Logger logger= LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;


    @RequestMapping(value = "/logi",method = RequestMethod.POST)
    @ResponseBody
        public BaseMessage login(@RequestBody Map<String,Object> map, HttpSession session){
//    public String login(@RequestBody Map<String,Object> map, HttpSession session){

        BaseMessage msg=new BaseMessage();
        String username=map.get("uname").toString();
        String password=map.get("upwd").toString();

        if(username!=null&&password!=null) {
                if ("succcess".equals( this.loginService.login(map))) {
                    session.setAttribute(GlobalConstants.USERNAME,username);
                    msg.setData("登陆成功");
                } else {
                    msg.setData("用户名或密码错误");
                }
        }else {
    msg.setData("用户名和密码不能为空");
}

return msg;
    }


    @RequestMapping(value = "/preview",method = RequestMethod.GET)
    @ResponseBody
    public void perview(HttpServletRequest req) throws Exception{
       Cookie[] cookies = req.getCookies();
       Claims a= GenerateTokens.parseJWT(cookies[0].getValue());
       String id=a.getSubject();
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST) @ResponseBody
    public BaseMessage register(@RequestBody Map<String, Object> map) {
        BaseMessage msg = new BaseMessage();
        msg.setData(this.loginService.register(map));
        return msg;
    }
}
