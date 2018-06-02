package com.library.user.service.impl;

import com.library.user.dao.IUserDao;
import com.library.user.model.User;
import com.library.user.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/4/3 0003.
 */
@Service("userService")
public class UserServiceImpl implements IUserService{
    @Resource
    private IUserDao userDao;
    public User selectUser(User user) {
        return this.userDao.selectUser(user);
    }

    public boolean addUser(User user) {
        boolean flag = true;
        try{
            userDao.addUser(user);
        }catch (Exception e){
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }
}
