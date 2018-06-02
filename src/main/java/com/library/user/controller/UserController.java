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

import javax.servlet.http.HttpServletRequest;
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
    @RequestMapping(value = "queryUser",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ResultModel queryUser(HttpServletRequest request, String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(EncryptUtil.encode(password));
        User old = userService.selectUser(user);
        System.out.println("oldUser"+old.getUsername());
        boolean key = false;
        if(old!=null){
            key = true;
            request.getSession().setAttribute("user",old);
        }
        return new ResultModel(key,old);
    }

    @RequestMapping(value = "addUser",method = {RequestMethod.GET,RequestMethod.POST})
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
                }
            }
        }
        return new ResultModel(key);
    }

    /**
     * 退出功能
     */
    @RequestMapping(value = "loginOut",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ResultModel loginOut(){
        HttpSession  session= HttpUtil.getSession();
        Enumeration enu= session.getAttributeNames();
        while (enu.hasMoreElements()){
            String name = (String) enu.nextElement();
            System.out.println("退出："+name);
            session.removeAttribute(name);
            System.out.println("分支测试");

        }
        return new ResultModel(true);
    }

}
