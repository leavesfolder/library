package com.library.user.dao;

import com.library.user.model.User;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/4/3 0003.
 */
@Component
public interface IUserDao {
    User selectUser(User user);
    void addUser(User user);

}
