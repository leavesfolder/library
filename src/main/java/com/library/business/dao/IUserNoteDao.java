package com.library.business.dao;

import com.library.business.model.UserNote;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2018/5/28 0028.
 */
@Component
public interface IUserNoteDao {
    List<UserNote> selectByFilter(UserNote userNote);
    void insert(UserNote userNote);
    void updateByPrimaryKeySelective(UserNote userNote);
}


