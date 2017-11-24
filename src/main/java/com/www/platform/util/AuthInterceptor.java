package com.www.platform.util;

import com.www.platform.constant.GlobalConstants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by upsmart on 17-11-20.
 */
public class AuthInterceptor implements HandlerInterceptor {

//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {


        Object sessionObj = request.getSession().getAttribute(GlobalConstants.USERNAME);
//        System.out.println("============"+request.getSession().getAttribute("uname"));
        if(sessionObj!=null) {
//            System.out.println("sessionObj"+sessionObj);
            return true;
        }
        response.sendRedirect("/views/login.html");
        System.out.println("myou session");
        return false;
    }




    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav) throws Exception { }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e) throws Exception { }

}
