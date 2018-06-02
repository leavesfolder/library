package com.library.user.controller;

import com.library.user.model.SysLoginLog;
import com.library.user.service.ISysLoginLogService;
import com.library.util.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/9 0009.
 */
@Controller
@RequestMapping("/autoEntry/SysLoginLog/")
public class SysLoginLogController {
    @Autowired
    private ISysLoginLogService sysLoginLogService;

    @RequestMapping(value = "queryLoginLog.do",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ResultModel queryLogin(String queryFlag){
        System.out.println(queryFlag);
        SysLoginLog sysLoginLog = new SysLoginLog();
        Map data = sysLoginLogService.queryLoginlog(sysLoginLog,queryFlag);
        return new ResultModel(data);
    }
    @RequestMapping(value = "getPagexx.do",method = {RequestMethod.GET,RequestMethod.POST})
    public String getPageXX(){
        String str = "这是一个测试";



        return "/test";
    }
}
