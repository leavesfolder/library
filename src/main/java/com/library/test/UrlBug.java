package com.library.test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2018/4/9 0009.
 */
public class UrlBug {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("thunder://QUFmdHA6Ly9qOmpAZy5keWdvZDE4LmNvbTo3OTc4LyVFNCVCOCU4QSVFNSVCOCU5RCVFNCVCOSU4QiVFNSU5RiU4RUJEJUU0JUI4JUFEJUU4JThCJUIxJUU1JThGJThDJUU1JUFEJTk3WyVFNyU5NCVCNSVFNSVCRCVCMSVFNSVBNCVBOSVFNSVBMCU4Mnd3dy5keTIwMTguY29tXS5tcDRaWg==");
        try {
//            InputStream is = url.openStream();
//            byte[] flush = new byte[1024];
//            int len;
//            while (-1!=(len=is.read(flush))){
//
//                System.out.println(new String(flush,0,len));
//            }
//            is.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("baidu.mp4"),"utf-8"));
            String mess = null;
            while (null!=(mess=br.readLine())){
                System.out.println(new String(mess));
                bw.append(mess);
                bw.newLine();
            }
            bw.flush();
            br.close();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
