package com.library.util;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/5/24 0024.
 */

/**
 * http的工具类
 */
public class HttpUtil {

    public static HttpServletRequest getReq(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }
    public static HttpServletResponse getResp(){
        HttpServletResponse resp = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        return resp;
    }

    public static HttpSession getSession(){
        return getReq().getSession();
    }
}
