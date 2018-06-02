package com.library.servlet;

/**
 * Created by Administrator on 2018/4/30 0030.
 */
public class test {
    public static void main(String[] args){
        String fileInfo = "Content-Disposition: form-data; name=\"myfile\"; filename=\"createExcel.txt\"";

        String tempFileName = fileInfo.substring(fileInfo.indexOf("filename")+10,fileInfo.lastIndexOf("."));
        System.out.println(tempFileName);
    }
}
