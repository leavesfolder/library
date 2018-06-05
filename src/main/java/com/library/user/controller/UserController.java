package com.library.user.controller;

import com.library.user.model.User;
import com.library.user.service.IUserService;
import com.library.util.EncryptUtil;
import com.library.util.HttpUtil;
import com.library.util.ResultModel;
import com.library.util.SequenceUtil;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2018/4/3 0003.
 */
@Controller
@RequestMapping("/autoEntry/User/")
public class UserController {
    @Autowired
    private IUserService userService;
    @RequestMapping(value = "queryUser.do",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ResultModel queryUser(HttpServletRequest request,HttpServletResponse response, String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(EncryptUtil.encode(password));
        User old = userService.selectUser(user);
        System.out.println("oldUser"+old.getUsername());
        boolean key = false;
        if(old!=null){
            key = true;
            setCookieAndSession(old);
        }
        return new ResultModel(key,old);
    }

    public static void setCookieAndSession(User user){
        HttpServletRequest request = HttpUtil.getReq();
        HttpServletResponse response = HttpUtil.getResp();
        request.getSession().setAttribute("user",user);
        //设置cookie
        String userAgent = request.getHeader("User-Agent");
        long now = System.currentTimeMillis();
        String token = EncryptUtil.encode(userAgent + now);
        Cookie cookie = new Cookie("token", now + "|" + token);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @RequestMapping(value = "addUser.do",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResultModel addUser(String userDetail){
        System.out.println(userDetail);
        boolean key = false;
        if(userDetail!=null){
            JSONArray jsonArray= JSONArray.fromObject(userDetail);
            List<User> users = JSONArray.toList(jsonArray,new User(),new JsonConfig());

            if (users!=null&&users.size()>0){
                for (User user:users){
                    System.out.println(user);
                    user.setRegTime(new Date());
                    user.setStatus(0);
                    user.setRole("01");
                    user.setId(SequenceUtil.getNextXxzjbh());
                    user.setPassword(EncryptUtil.encode(user.getPassword()));
                    key = userService.addUser(user);
                    if (key){
                        setCookieAndSession(user);
                    }
                }
            }
        }
        return new ResultModel(key);
    }

    /**
     * 退出功能
     */
    @RequestMapping(value = "loginOut.do",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ResultModel loginOut(HttpServletResponse response){
        HttpSession  session= HttpUtil.getSession();
        Enumeration enu= session.getAttributeNames();
        while (enu.hasMoreElements()){
            String name = (String) enu.nextElement();
            session.removeAttribute(name);
        }
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(-1);//删除cookie
        cookie.setPath("/");
        response.addCookie(cookie);
        return new ResultModel(true);
    }

}
