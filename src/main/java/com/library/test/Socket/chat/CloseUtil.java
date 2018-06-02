package com.library.test.Socket.chat;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2018/4/12 0012.
 */
public class CloseUtil {
    public static void closeAll(Closeable... io){
        for (Closeable temp:io){
            if (null!=temp){
                try {
                    temp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
