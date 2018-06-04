package com.library.interceptor;

import com.library.util.EncryptUtil;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by liuys on 2018/6/4 0004.
 * 创建该类是为了补充SpringMVC拦截器的不足，SpringMVC拦截器只能拦截Controller,
 * 并不能进行页面的拦截，基于此创建该类作为访问控制的一个补充
 *
 * filter是在servlet前执行的，所以先执行filter后执行拦截器。
 * 访问页面时都进行过滤验证，如果存在该用户session，
 * 则访问该页面，否则跳转到登陆页面登录，保存session后访问其它页面，
 */
public class LoginFilter implements Filter{
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) res;
        //除了welcome.html, login.html,error.jsp其他的html都拦截
        StringBuffer url = request.getRequestURL();

        String path = url.toString();
        //拦截 *.html 和 *.do
        if(path.endsWith(".html") || path.endsWith(".do")||path.endsWith(".jsp")){
            //放过 login.html
            if(path.endsWith("login.html")||path.endsWith("welcome.html")||path.endsWith("error.jsp")){
                chain.doFilter(req, res);
                return;
            }
            //放过 /autoEntry/User/*.do
            if(path.indexOf("/User/")>0){
                chain.doFilter(req, res);
                return;
            }
            //如果没有登录就转到登录页面
            processAccessControl(request,response, chain);
            return;
        }
        //放过其他的请求
        chain.doFilter(req, res);


    }

    /**
     * 处理访问控制
     * @throws IOException
     * @throws ServletException
     */
    private void processAccessControl(HttpServletRequest request,
                                      HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //检查Cookie中是否有Token 没有就去登录页面
        Cookie[] cookies=request.getCookies();
        Cookie token = null;
        //找到token cookie
        for (Cookie cookie : cookies) {
            if("token".equals(cookie.getName())){
                token = cookie;
                break;
            }
        }
        //处理token是否合理 ...
        String value = token.getValue();
        if(token==null||value==null||"".equals(value)){
            //如果没有找到，就重定向到登录页面
            String path=request.getContextPath();
            String login = path+"/html/login.html";
            response.sendRedirect(login);
            return;
        }

        String[] data = value.split("\\|");
        String time = data[0];
        String md5=data[1];
        String userAgent=request.getHeader("User-Agent");
        //检查上次的MD5 与本次的md5是否一致
        //如果一致就说明上次来过的用户，token
        //是合格的准予通过
        if(md5.equals( EncryptUtil.encode(userAgent + time))){
            chain.doFilter(request, response);
            return;
        }
        //如果token验证不合理，就重定向到登录页面
        String path=request.getContextPath();
        String login = path+"/html/login.html";
        response.sendRedirect(login);
    }

    public void destroy() {

    }
}
