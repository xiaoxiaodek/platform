package com.www.platform.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by upsmart on 17-11-15.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午12:21
 */
public class CORSFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws
        IOException, ServletException {
        HttpServletResponse response =(HttpServletResponse) res;
        HttpServletRequest request=(HttpServletRequest) req;
        String Origin=(String)request.getHeader("Origin");
        if(Origin=="http://localhost:2333"||Origin=="http://192.168.199.113:2333"||Origin=="http://192.168.199.110:2333"){
            response.setHeader("Access-Control-Allow-Origin",Origin);
        };
        response.setHeader("Access-Control-Allow-Origin",Origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(req,response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
