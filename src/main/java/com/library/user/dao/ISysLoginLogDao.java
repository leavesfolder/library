package com.library.user.dao;

import com.library.user.model.SysLoginLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by Administrator on 2018/4/9 0009.
 */
@Component
public interface ISysLoginLogDao {
   List<SysLoginLog> selectBySysLogin(SysLoginLog sysLoginLog);
   void insert(SysLoginLog sysLoginLog);

}
