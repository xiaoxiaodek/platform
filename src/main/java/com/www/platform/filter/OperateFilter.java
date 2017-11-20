package com.www.platform.filter;

import com.www.platform.dao.LogMapper;
import com.www.platform.entity.Log;
import com.www.platform.util.GetLocalIp;
import com.www.platform.util.SystemLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.lang.reflect.Method;

import org.aspectj.lang.reflect.MethodSignature;


/**
 * Created by upsmart on 17-11-14.
 * @desc springmvc配置aop对cotroller进行拦截记录日志
 */

@Aspect
//@Component
public class OperateFilter {

    private  static  final Logger logger = LoggerFactory.getLogger(OperateFilter.class);
    //注入service,用来将日志信息保存在数据库
    @Resource(name="logMapper")
    private LogMapper logMapper;

    Log log = new Log();

    //配置接入点,如果不知道怎么配置,可以百度一下规则
    @Pointcut("execution(* com.www.platform.controller..*.*(..))")
    private void controllerAspect(){}//定义一个切入点

    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("=====SysLogAspect前置通知开始=====");
    }

    @After("controllerAspect()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("=====SysLogAspect前置通知结束=====");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        int uid = (Integer) request.getSession().getAttribute("uname");
        Integer comid = (Integer) request.getSession().getAttribute("comid");
        if(comid != null)
            log.setComid(comid);
        logMapper.insert(log);
    }

    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //获取登录用户账户
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        int uid = (Integer) request.getSession().getAttribute("uname");
        //获取系统ip,这里用的是我自己的工具类,可自行网上查询获取ip方法
        log.setUname("yy");
        String ip = GetLocalIp.getIpAddr(request);
        log.setIp(ip);
        //获取系统时间
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        log.setOtime(time);
        //方法通知前获取时间,为什么要记录这个时间呢？当然是用来计算模块执行时间的
        long start = System.currentTimeMillis();
        // 拦截的实体类，就是当前正在执行的controller
        Object target = pjp.getTarget();
        // 拦截的方法名称。当前正在执行的方法
        String methodName = pjp.getSignature().getName();
        // 拦截的方法参数
        Object[] args = pjp.getArgs();
        // 拦截的放参数类型
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Class[] parameterTypes = msig.getMethod().getParameterTypes();

        Object object = null;
        // 获得被拦截的方法
        Method method = null;
        try {
            method = target.getClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (null != method) {
            // 判断是否包含自定义的注解，说明一下这里的SystemLog就是我自己自定义的注解
            if (method.isAnnotationPresent(SystemLog.class)) {
                SystemLog systemlog = method.getAnnotation(SystemLog.class);
                log.setModule(systemlog.module());
                log.setMethod(systemlog.methods());
                try {
                    object = pjp.proceed();
                    long end = System.currentTimeMillis();
                    //将计算好的时间保存在实体中
                    log.setResponsetime(""+(end-start));
                    log.setResult("执行成功！");
                    //保存进数据库
                } catch (Throwable e) {
                    // TODO Auto-generated catch block
                    long end = System.currentTimeMillis();
                    log.setResponsetime(""+(end-start));
                    log.setResult("执行失败");
                }
            } else {//没有包含注解
                object = pjp.proceed();
            }
        } else { //不需要拦截直接执行
            object = pjp.proceed();
        }
        return object;
    }
}
