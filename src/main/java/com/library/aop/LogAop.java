package com.library.aop;

import com.library.user.model.User;
import com.library.user.service.ISysLoginLogService;
import com.library.util.HttpUtil;
import com.library.util.ResultModel;
import com.sun.deploy.net.HttpUtils;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/5/23 0023.
 */
@Component
@Aspect
public class LogAop {
    @Autowired
    private ISysLoginLogService sysLoginLogService;

    @Around("bean(userController)")
    public Object process(ProceedingJoinPoint joinPoint){
        try{
            System.out.println("开始进入AOP记录日志");
            Object obj = joinPoint.proceed();
            HttpSession session=HttpUtil.getSession();
            //获取登录用户信息
            User user = (User) session.getAttribute("user");
            if(user!=null){
                sysLoginLogService.insertLoginlog(user);
            }else{
                System.out.println("用户未登录");
            }
            System.out.println("AOP正常结束");
            return obj;
        }catch (Throwable e){
            e.printStackTrace();
            System.out.println("发生异常！");
            return new ResultModel(false);
        }


    }

}
