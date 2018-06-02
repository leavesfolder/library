package com.library.servlet;



import com.jspsmart.upload.SmartUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2018/5/1 0001.
 */
@WebServlet(name = "SmartUploadServlet",urlPatterns = "/html/smartUpload.do")
public class SmartUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取上传文件保存路径
        String path = getServletContext().getRealPath("/")+"img/ImageUpload/";
        System.out.println(path);
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }
        SmartUpload su = new SmartUpload();
        System.out.println(su);
        //初始化对象
        su.initialize(getServletConfig(),req,resp);
        //设置允许上传文件大小
        su.setMaxFileSize(1024*1024*10);
        //设置允许上传文件总大小
        su.setTotalMaxFileSize(1024*1024*100);
        //设置允许上传文件类型
        su.setAllowedFilesList("txt,jpg,png");
        String result = "上传成功！";
        //设置禁止上传文件类型
        try {
            su.setDeniedFilesList("jsp,rar");
            su.upload();
            int count=su.save(path);
            System.out.println("上传成功+"+count+"个文件！");
            for (int i=0;i<count;i++){
                com.jspsmart.upload.File tempfile=su.getFiles().getFile(i);
                System.out.println(tempfile.getFileName());
                System.out.println(tempfile.getFilePathName());
            }

        }catch (Exception e){
            result="上传失败！";

            if (e.getMessage().indexOf("1105")>-1){
                result="上传文件大小超限！";
            }else if(e.getMessage().indexOf("1110")>-1){
                result="上传文件总大小超限！";
            }else if (e.getMessage().indexOf("1010")>-1){
                result="上传文件类型是不被允许的！";
            }
            e.printStackTrace();
        }
        req.setAttribute("result",result);
        req.getRequestDispatcher("smartUpload.jsp").forward(req,resp);

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
