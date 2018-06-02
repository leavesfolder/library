package com.library.user.service.impl;

import com.library.user.dao.ISysLoginLogDao;
import com.library.user.model.SysLoginLog;
import com.library.user.model.User;
import com.library.user.service.ISysLoginLogService;
import com.library.util.SequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/9 0009.
 */
@Service("sysLoginLogService")
public class SysLoginLogImpl implements ISysLoginLogService {
    @Autowired
    private ISysLoginLogDao loginDao;

    /**
     * 本方法用于根据角色统计访问量
     * @param sysLoginLog
     * @return
     */
    public Map<String,Integer> queryLoginlog(SysLoginLog sysLoginLog,String queryFlag) {
        List<SysLoginLog> loginList = loginDao.selectBySysLogin(sysLoginLog);
        Map<String,Integer> data = new HashMap<String, Integer>();
        int sum =0;
        if ("role".equals(queryFlag)){
            for (SysLoginLog login:loginList) {
                if (data.containsKey(login.getRole())){
                    sum = data.get(login.getRole());
                    data.put(login.getRole(),++sum);
                }else{
                    data.put(login.getRole(),1);
                }
            }
        }else if ("city".equals(queryFlag)){
            String cityName = "";
            String simpleName = "";
            for (SysLoginLog login:loginList) {
                cityName = login.getCityname();
                System.out.println(cityName);
                if (cityName!=null&&!"".equals(cityName)){
                    System.out.println(1111);
                    if (cityName.contains("黑龙江")||cityName.contains("内蒙古")){
                        simpleName = cityName.substring(0,3);
                    }else{
                        simpleName = cityName.substring(0,2);
                    }

                }
                System.out.println(simpleName);
                if (data.containsKey(simpleName)){
                    sum = data.get(simpleName);
                    data.put(simpleName,++sum);
                }else{
                    data.put(simpleName,1);
                }


            }
        }
        return data;
    }

    /**
     * 插入登录日志信息
     * @param user
     */

    public void insertLoginlog(User user) {
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setRole(user.getRole());
        sysLoginLog.setIp(user.getRegIp());
        sysLoginLog.setYhid(user.getId());
        sysLoginLog.setYhxm(user.getUsername());
        sysLoginLog.setCitycode(user.getRegCityCode());
        sysLoginLog.setCitycode(user.getRegCityCode());
        sysLoginLog.setRzsj(new Date());
        sysLoginLog.setXxbh(SequenceUtil.getNextXxzjbh());
        loginDao.insert(sysLoginLog);
    }
}
