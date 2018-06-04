package com.library.business.controller;

import com.library.business.model.UserNote;
import com.library.business.service.IUserNoteService;
import com.library.util.ResultModel;
import com.library.util.SequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/5/28 0028.
 */
@Controller
@RequestMapping("/autoEntry/UserNote/")
public class UserNoteController {
    @Autowired
    private IUserNoteService userNoteService;

    @RequestMapping(value = "queryUserNote.do",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ResultModel getNoteByUserid(UserNote userNote){
        boolean key = true;
        userNote.setScbz(0);
        List<UserNote> noteList = userNoteService.queryUserNote(userNote);
        if (noteList==null){
            key = false;
        }
        return new ResultModel(key,noteList);
    }

    @RequestMapping(value = "insertUserNote.do",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ResultModel addNote(@Valid UserNote userNote){
        System.out.println(userNote);
        System.out.println(userNote.getTitle());
        System.out.println(userNote.getBody());
        userNote.setId(SequenceUtil.getNextXxzjbh());
        userNote.setCreatetime(new Date());
        userNote.setScbz(0);
        boolean flag = userNoteService.addUserNote(userNote);
        return new ResultModel(flag);
    }

    @RequestMapping(value = "updateUserNote.do",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ResultModel updateNote(@Valid UserNote userNote){
        System.out.println(userNote.getBody());
        userNote.setLastmodifytime(new Date());
        boolean flag = userNoteService.updateUserNote(userNote);
        return new ResultModel(flag);
    }
}
