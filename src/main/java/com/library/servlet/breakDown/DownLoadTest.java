package com.library.servlet.breakDown;

/**
 * Created by Administrator on 2018/5/6 0006.
 */
public class DownLoadTest {
    /**
     * @param args
     */
    public static void main(String[] args) {

        String filepath = "http://127.0.0.1:8080/library/file/[破晓电影www.poxiao.com]电锯惊魂8HD高清中英双字.mp4";
        MultiTheradDownLoad load = new MultiTheradDownLoad(filepath ,10);
        load.downloadPart();
    }
}
