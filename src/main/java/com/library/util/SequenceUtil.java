package com.library.util;

import java.util.Random;
import java.util.UUID;

/**
 * Created by Administrator on 2018/5/24 0024.
 */
public class SequenceUtil {
    /**
     * 获取信息主键编号：暂定规则，20位
     * 前8位为UUID的前八位
     * 9-14位为当前时间秒数的后六位，
     * 15-20位为字母小写和0-9的随机混合
     */
    public static String getNextXxzjbh(){
        StringBuffer xxzjbh = new StringBuffer();
        xxzjbh.append(UUID.randomUUID().toString().substring(0,8));
        String now = String.valueOf(System.currentTimeMillis());
        xxzjbh.append(now.substring(0,6));
        String sail="abcdefjhijklmnopqrstuvwxyz0123456789";
        Random random=new Random();
        for (int i=0;i<8;i++){
            int index=random.nextInt(36);
            xxzjbh.append(sail.charAt(index));
        }
        return xxzjbh.toString();
    }
}
