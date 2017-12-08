package com.www.platform.controller.user;

import com.www.platform.constant.GlobalConstants;
import com.www.platform.entity.User;
import com.www.platform.message.BaseMessage;

import com.www.platform.message.MessageCode;
import com.www.platform.message.StatusCode;
import com.www.platform.service.user.LoginService;
import com.www.platform.util.ResponseUtil;
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
            User user=this.loginService.login(map);
            if (user!=null){
                session.setAttribute(GlobalConstants.USERNAME,username);
                session.setAttribute(GlobalConstants.UID,user.getUid());
                session.setAttribute(GlobalConstants.ROLE,user.getRole());
                ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
                msg.setData(user.getRole());
//                msg.setData("登陆成功");
            } else {
                msg.setData("用户名或密码错误");
                System.out.println("msg     :    "+msg.getData());
                ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
            }
        }else {
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
            msg.setData("用户名和密码不能为空");
        }
        System.out.println("session.getAttribute(GlobalConstants.ROLE)============="+session.getAttribute(GlobalConstants.ROLE));
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
        if("新建成功".equals(msg.getData())){
            ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
        }else {
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
        }
        return msg;
    }



    /**
     * 注册
     * @param map
     * @return
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST) @ResponseBody
    @SystemLog(module="用户管理",methods="删除用户")

    public BaseMessage deleteUser(@RequestBody Map<String, Object> map,HttpSession session) {
        BaseMessage msg = new BaseMessage();
        String uname = (String) session.getAttribute(GlobalConstants.USERNAME);
        if(uname!=null) {
        msg.setData(this.loginService.deleteUser(map));
        if("删除成功".equals(msg.getData())){
            ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
        }else if("不能删除管理员".equals(msg.getData())){
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
        }else {
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);

        }
    }else {
        msg.setData("请登录后再修改");
        ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
    }
        return msg;
    }



    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping(value = "/selectAllUser", method = RequestMethod.GET) @ResponseBody
    @SystemLog(module="用户管理",methods="查询所有用户")

    public BaseMessage selectAllUser(HttpSession session) {
        BaseMessage msg = new BaseMessage();
        if(Integer.parseInt(session.getAttribute(GlobalConstants.ROLE).toString())==1){
        String uname = (String) session.getAttribute(GlobalConstants.USERNAME);
        if(uname!=null) {
        msg.setData(this.loginService.selectAllUser());
        if(msg.getData()!=null){
            ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
        }else {
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
        }
    }else {
        msg.setData("请登录后再修改");
        ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
    }
        }else {
            msg.setData("不给你查");
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
        }
        return msg;
    }






    /**
     * 根据用户名查询用户
     *
     * @return
     */
    @RequestMapping(value = "/selectUserByUname", method = RequestMethod.POST) @ResponseBody
    @SystemLog(module="用户管理",methods="根据用户名查询用户")

    public BaseMessage selectUserByUname(@RequestBody Map<String, Object> map,HttpSession session) {
        BaseMessage msg = new BaseMessage();
        System.out.println("===========进去了吗");
        String uname = (String) session.getAttribute(GlobalConstants.USERNAME);
        if(uname!=null) {
        msg.setData(this.loginService.selectLikeUname(map));
        if(msg.getData()!=null){
            ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
        }else {
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
        }
    }else {
        msg.setData("请登录后再修改");
        ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
    }
        return msg;
    }




    /**
     * 修改密码
     * @param map
     * @return
     */
    @RequestMapping(value = "editPassword", method = RequestMethod.POST) @ResponseBody
    @SystemLog(module="用户管理",methods="修改密码")

    public BaseMessage editPassword(@RequestBody Map<String, Object> map, HttpSession session) {
        BaseMessage msg = new BaseMessage();
        String uname = (String) session.getAttribute(GlobalConstants.USERNAME);
        if(uname!=null) {
            map.put("uname", uname);
            msg.setData(this.loginService.editPassword(map));
            if("修改成功".equals(msg.getData())){
                ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);

            }else {
                ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
            }

        }else {
            msg.setData("请登录后再修改");
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
        }
        return msg;
    }










    /**
     * 修改权限
     * @param map
     * @return
     */
    @RequestMapping(value = "/editRoleAndEmail", method = RequestMethod.POST) @ResponseBody
    @SystemLog(module="用户管理",methods="修改邮箱和权限")

    public BaseMessage editRoleAndEmail(@RequestBody Map<String, Object> map, HttpSession session) {
        BaseMessage msg = new BaseMessage();
        String uname = (String) session.getAttribute(GlobalConstants.USERNAME);
        if(uname!=null) {
            map.put("uname", uname);
            msg.setData(this.loginService.editRoleAndEmail(map));
            if("修改成功".equals(msg.getData())){
                ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
            }else {
                ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
            }
        }else {
            msg.setData("请登录后再修改");
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.NO_RESPONSE);
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
            session.removeAttribute(GlobalConstants.UID);
            session.removeAttribute(GlobalConstants.ROLE);
            System.out.println("++++++++++++++="+session.getAttribute(GlobalConstants.USERNAME));
            session.invalidate();
            msg.setData("success");
            ResponseUtil.buildResMsg(msg, MessageCode.SUCCESS, StatusCode.SUCCESS);
        } catch (NullPointerException e) {
            logger.error("退出异常");
            msg.setData("退出异常");
            ResponseUtil.buildResMsg(msg, MessageCode.FAILED, StatusCode.SYSTEM_ERROR);
        }
        return msg;
    }


}
