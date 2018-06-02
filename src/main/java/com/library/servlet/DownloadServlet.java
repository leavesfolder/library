package com.library.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2018/5/1 0001.
 */
@WebServlet(name = "DownloadServlet",urlPatterns = "/html/downloadServlet.do")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      this.doGet(req,resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取文件路径
       String path = getServletContext().getRealPath("/")+"/img/ImageUpload/";
       String fileName = req.getParameter("filename");
       File file = new File(path+fileName);
       if (file.exists()){
           //设置文件类型或者：application/octet-stream
           resp.setContentType("application/x-msdownload");
           //设置头信息
           resp.setHeader("Content-Disposition","attachment;filename=\""+fileName+"\"");
           InputStream inputStream = new FileInputStream(file);
           ServletOutputStream sos = resp.getOutputStream();
           byte b[] = new byte[1024];
           int len;
           while ((len=inputStream.read(b))!=-1){
               sos.write(b,0,len);
           }
           //关闭流
           inputStream.close();
           sos.close();


       }else{
           req.setAttribute("errorResult","文件不存在！");
           System.out.println("文件不存在");
           RequestDispatcher requestDispatcher = req.getRequestDispatcher("uploadImage.html");
           requestDispatcher.forward(req,resp);
       }
    }
}
