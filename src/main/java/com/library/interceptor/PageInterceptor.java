package com.library.interceptor;

import com.library.user.model.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/5/22 0022.
 */
public class PageInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object o) throws Exception {
        /**
         * 首先进入的方法，return false表示拦截，不向下执行，return true表示向下执行
         */
        HttpSession session = req.getSession();
        System.out.println("session"+session);
        User user = (User) session.getAttribute("user");
        System.out.println("user"+user);
        if (user!=null){
            return true;
        }else{
            System.out.println("未登录被拦截。。。");
            RequestDispatcher rd =req.getRequestDispatcher("/html/welcome.html");
            rd.forward(req,resp);
            return false;
        }
    }
    //返回ModelAndView之前执行的方法
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
         System.out.println("postHandler");
    }

    //执行Handler完之后执行此方法
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion");
    }
}
