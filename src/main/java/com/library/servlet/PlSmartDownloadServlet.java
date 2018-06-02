package com.library.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

/**
 * 批量下载
 */
@WebServlet(name = "PlSmartDownloadServlet",urlPatterns = "/html/plSmartDownload.do")
public class PlSmartDownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/x-msdownload");
        resp.setHeader("Content-Disposition","attachment;filename=createExcel.zip");
        String path = getServletContext().getRealPath("/")+"img/ImageUpload/";
        String[] filenames = req.getParameterValues("filename");
        ZipOutputStream zos = new ZipOutputStream(resp.getOutputStream());
        for (String filename:filenames){
            File file= new File(path+filename);
            zos.putNextEntry(new ZipEntry(filename));
            FileInputStream fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int n;
            while ((n=fis.read(b))!=-1){
                zos.write(b,0,n);
            }
            fis.close();
        }
        zos.finish();
        zos.close();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
