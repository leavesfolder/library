package com.library.servlet;

import com.jspsmart.upload.SmartUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/5/2 0002.
 */
@WebServlet(name = "SmartDownloadServlet",urlPatterns = "/html/smartDownload.do")
public class SmartDownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("filename");
        SmartUpload su = new SmartUpload();
        su.initialize(getServletConfig(),req,resp);
        try{
            su.downloadFile("/img/ImageUpload/"+fileName);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
