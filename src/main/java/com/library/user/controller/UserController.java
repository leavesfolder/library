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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Administrator on 2018/4/3 0003.
 */
@Controller
@RequestMapping("/autoEntry/User/")
public class UserController {
    @Autowired
    private IUserService userService;
    private String message;
    @RequestMapping(value = "queryUser.do",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ResultModel queryUser(@Valid User user){
        String emiORpho = user.getEmiORpho();
        String password = user.getPassword();
        String code = user.getCode();
        if (emiORpho==null||"".equals(emiORpho)){
            return new ResultModel(false,"邮箱或者手机号不能为空！");
        }
        if (password==null||"".equals(password)){
            return new ResultModel(false,"密码不能为空！");
        }
        if (!checkCode(code)){
            return new ResultModel(false,"验证码出错！");
        }

        User logCheck = new User();
        if(emiORpho.indexOf("@")>-1){
            //登录名为邮箱
            logCheck.setEmail(emiORpho);
        }else{
            //登录名为手机号
            logCheck.setPhone(emiORpho);
        }

        User old = userService.selectUser(logCheck);
        boolean key = false;
        if(old!=null){
            if(old.getPassword().equals(EncryptUtil.encode(password))){
                key = true;
                setCookieAndSession(user);
            }else {
                message = "密码错误，请核对！";
            }
        }else{
            message = "账号不存在,请先注册！";
        }
        return new ResultModel(key,old,message);
    }
    public static boolean checkCode(String code){
        boolean flag = false;
        String servCode = (String) HttpUtil.getSession().getAttribute("code");
        if(servCode!=null&&code!=null&&!"".equals(code)){
            if (code.equalsIgnoreCase(servCode)){
                flag=true;
            }
        }
        return flag;
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
        boolean key = false;
        if(userDetail!=null){
            JSONArray jsonArray= JSONArray.fromObject(userDetail);
            List<User> users = JSONArray.toList(jsonArray,new User(),new JsonConfig());
            if (users!=null&&users.size()>0){
                for (User user:users){
                    if (!checkCode(user.getCode())){
                        return new ResultModel(false,"验证码出错！");
                    }
                    /**
                     * 这里需要处理的逻辑是判断手机号或者邮箱是否已经被使用
                     * 要进行这个判断就需要根据手机或者邮箱单独进行查询
                     * 先根据邮箱验证，邮箱没有使用，继续手机号验证
                     */
                    User emiCheck = new User();
                    emiCheck.setEmail(user.getEmail());
                    User old = userService.selectUser(emiCheck);
                    if (old!=null){
                        key = false;
                        message = "该邮箱已经被使用过,请尝试其他邮箱或者登陆";
                    }else {
                        //old==null,则继续验证手机号
                        User phoCheck = new User();
                        phoCheck.setPhone(user.getPhone());
                        old = userService.selectUser(phoCheck);
                        if (old!=null){
                            key = false;
                            message = "该手机号已经被使用过,请尝试其他手机号或者登陆";
                        }else{
                            //手机号验证也通过，则进行插入操作
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
            }
        }
        return new ResultModel(key,message);
    }


    @RequestMapping(value = "code.do", produces = "image/png")
    @ResponseBody
    public byte[] code(HttpServletRequest request) throws Exception {
        byte[] buf;
        BufferedImage img = new BufferedImage(80, 30, BufferedImage.TYPE_3BYTE_BGR);
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                img.setRGB(x, y, 0xEEEEEE);
            }
        }
        for (int i = 0; i < 600; i++) {
            int x = (int) (Math.random() * 80);
            int y = (int) (Math.random() * 30);
            int rgb = (int) (Math.random() * 0xffffff);
            img.setRGB(x, y, rgb);
        }
        Graphics2D g = img.createGraphics();
        int rgb = (int) (Math.random() * 0xffffff);
        g.setColor(new Color(rgb));
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 25);
        g.setFont(font);

        // 抗锯齿，平滑绘制
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        int x = (int) (Math.random() * 10);
        int y = (int) (Math.random() * 5);
        String code = randomCode();
        request.getSession().setAttribute("code", code);

        g.drawString(code, 3 + x, 28 - y);
        // 绘制5条乱线
        for (int i = 0; i < 5; i++) {
            int x1 = (int) (Math.random() * 80);
            int x2 = (int) (Math.random() * 80);
            int y1 = (int) (Math.random() * 30);
            int y2 = (int) (Math.random() * 30);
            rgb = (int) (Math.random() * 0xffffff);
            g.setColor(new Color(rgb));
            g.drawLine(x1, y1, x2, y2);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(img, "png", out);
        out.close();
        buf = out.toByteArray();
        return buf;

    }

    public String randomCode() {
        String chs = "4567ABCDEFHJKLXYabcdrhknp";
        char[] code = new char[4];
        for (int i = 0; i < code.length; i++) {
            int index = (int) (Math.random() * chs.length());
            code[i] = chs.charAt(index);
        }
        return new String(code);
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
