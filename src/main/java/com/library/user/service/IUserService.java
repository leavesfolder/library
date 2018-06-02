package com.library.user.service;

import com.library.user.model.User;

/**
 * Created by Administrator on 2018/4/3 0003.
 */
public interface IUserService {
    public User selectUser(User user);
    boolean addUser(User user);
}
