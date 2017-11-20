package com.www.platform.controller.user;

import com.www.platform.constant.GlobalConstants;
import com.www.platform.message.BaseMessage;
import com.www.platform.service.LoginService;
import com.www.platform.util.SystemLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by upsmart on 17-11-13.
 *
 * @author zjl
 * @version 0.0
 */
@Controller
@RequestMapping("user")
public class LoginController {
    private static Logger logger= LoggerFactory.getLogger(
        LoginController.class);
    @Autowired
    private LoginService loginService;

    /**
     * 登陆
     * @param map
     * @param session
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(module="用户管理",methods="登陆")

    public BaseMessage login(@RequestBody Map<String,Object> map, HttpSession session){
        BaseMessage msg=new BaseMessage();
        String username=map.get("uname").toString();
        String password=map.get("upwd").toString();

        if(username!=null&&password!=null) {
            String uid=this.loginService.login(map);
                if (!("fail".equals(uid )) ){
                    session.setAttribute(GlobalConstants.USERNAME,username);
                    session.setAttribute(GlobalConstants.UID,Integer.parseInt(uid));
                    msg.setData("登陆成功");
                } else {
                    msg.setData("用户名或密码错误");
                }
        }else {
    msg.setData("用户名和密码不能为空");
}

return msg;
    }

    /**
     * 注册
     * @param map
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST) @ResponseBody
    @SystemLog(module="用户管理",methods="注册")

    public BaseMessage register(@RequestBody Map<String, Object> map) {
        BaseMessage msg = new BaseMessage();
        msg.setData(this.loginService.register(map));
        return msg;
    }




    /**
     * 修改密码
     * @param map
     * @return
     */
    @RequestMapping(value = "editPassword", method = RequestMethod.POST) @ResponseBody
    @SystemLog(module="用户管理",methods="修改密码")

    public BaseMessage editPassword(@RequestBody Map<String, Object> map, HttpSession sesssion) {
        BaseMessage msg = new BaseMessage();
        String uname = (String) sesssion.getAttribute(GlobalConstants.USERNAME);
        if(uname!=null) {
            map.put("uname", uname);
            msg.setData(this.loginService.editPassword(map));

        }else {
            msg.setData("请登录后再修改");
        }
        return msg;
    }


    /**
     * 修改其他信息
     * @param map
     * @return
     */
    @RequestMapping(value = "editInfo", method = RequestMethod.POST) @ResponseBody
    @SystemLog(module="用户管理",methods="修改信息")

    public BaseMessage editInfo(@RequestBody Map<String, Object> map, HttpSession sesssion) {
        BaseMessage msg = new BaseMessage();
        String uname = (String) sesssion.getAttribute(GlobalConstants.USERNAME);
        if(uname!=null) {
            map.put("uname", uname);
            msg.setData(this.loginService.editInfo(map));

        }else {
            msg.setData("请登录后再修改");
        }
        return msg;
    }





    /**
     * 退出系统
     * @param session
     * @return
     */    @RequestMapping(value = "/logout", method = RequestMethod.GET) @ResponseBody
    @SystemLog(module="用户管理",methods="退出登录")

    public BaseMessage logout(HttpSession session) {
        BaseMessage msg = new BaseMessage();
        try {
            session.removeAttribute(GlobalConstants.USERNAME);
            session.invalidate();
            msg.setData("success");
        } catch (NullPointerException e) {
            logger.error("退出异常");
        }
        return msg;
    }


}
