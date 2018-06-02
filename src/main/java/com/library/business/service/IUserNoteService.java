package com.library.business.service;

import com.library.business.model.UserNote;

import java.util.List;

/**
 * Created by Administrator on 2018/5/28 0028.
 */
public interface IUserNoteService {
    boolean addUserNote(UserNote userNote);
    List<UserNote> queryUserNote(UserNote userNote);
    boolean updateUserNote(UserNote userNote);
}
