package com.library.business.service.impl;

import com.library.business.dao.IUserNoteDao;
import com.library.business.model.UserNote;
import com.library.business.service.IUserNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/5/28 0028.
 */
@Service("userNoteService")
public class UserNoteImpl implements IUserNoteService {
    @Resource
    private IUserNoteDao userNoteDao;

    public boolean addUserNote(UserNote userNote) {
        boolean flag = true;
        try{
            userNoteDao.insert(userNote);
        }catch (Exception e){
            flag = false;
            e.printStackTrace();
        }

        return flag;
    }

    public List<UserNote> queryUserNote(UserNote userNote) {
        List<UserNote> noteList = null;
        try{
           noteList = userNoteDao.selectByFilter(userNote);
        }catch (Exception e){
            e.printStackTrace();
        }
        return noteList;
    }

    public boolean updateUserNote(UserNote userNote) {
        boolean flag = true;
        try{
            userNoteDao.updateByPrimaryKeySelective(userNote);
        }catch (Exception e){
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }
}
