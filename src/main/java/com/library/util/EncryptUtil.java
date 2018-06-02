package com.library.util;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

/**
 * Created by Administrator on 2018/4/6 0006.
 */
public class EncryptUtil {
    private static final String salt="com.leavesfolder.cn";
    public static String encode(String str){
        try{
            byte[] data = str.getBytes("utf-8");
            MessageDigest md=
                    MessageDigest.getInstance("md5");
            md.update(data);
            md.update(salt.getBytes("utf-8"));
            byte[] md5=md.digest();
            String code=
                    Base64.encodeBase64String(md5);
            return code;
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }
    }
//    public static void main(String[] sdf){
//        System.out.println(encode("123456"));
//        System.out.println(encode("123456"));
//    }
}
