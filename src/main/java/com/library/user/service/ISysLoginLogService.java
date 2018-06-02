package com.library.user.service;

import com.library.user.model.SysLoginLog;
import com.library.user.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/9 0009.
 */
public interface ISysLoginLogService {
    Map<String,Integer> queryLoginlog(SysLoginLog loginLog,String queryFlag);
    void insertLoginlog(User user);
}
