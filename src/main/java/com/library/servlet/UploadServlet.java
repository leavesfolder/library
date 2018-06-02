package com.library.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Properties;

/**
 * Created by Administrator on 2018/4/26 0026.
 */
@WebServlet(name = "UploadServlet",urlPatterns = "/html/uploadServlet.do",loadOnStartup = 1)
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Properties properties = new Properties();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("servletFileInfo.properties");
        try {
            properties.load(is);
            String fileName = properties.getProperty("fileName");
            String filePath = properties.getProperty("filePath");
            File tempFile = new File(filePath+fileName);
            if (!tempFile.exists()){
                tempFile.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(tempFile);
            InputStream reqIs = req.getInputStream();
            byte[] data = new byte[1024];
            int flush;
            while ((flush=reqIs.read(data))!=-1){
                fos.write(data,0,flush);
            }
            fos.close();
            reqIs.close();
            is.close();



            //获取上传文件名
            RandomAccessFile raf = new RandomAccessFile(tempFile,"r");

            raf.readLine();
            String fileInfo = raf.readLine();
            String tempFileName = new String(fileInfo.substring(fileInfo.indexOf("filename")+10, fileInfo.lastIndexOf(".")+4).getBytes("iso-8859-1"),"UTF-8");
            System.out.println(tempFileName);

            //获取文件上传内容
            raf.seek(0);//首先从文件头开始读取
            long startPosition = 0;
            int i=1;
            while ((flush=raf.readByte())!=-1&&i<=4){
                if (flush=='\n'){
                    startPosition = raf.getFilePointer();
                    i++;
                }
            }
            //startPosition-=1;

            //获取文件接收内容结束位置
            raf.seek(tempFile.length());
            long endPosition = raf.getFilePointer();
            System.out.println(endPosition);
            int j=1;
            while (endPosition>=0&&j<=2){
                endPosition--;
                raf.seek(endPosition);
                if (raf.readByte()=='\n'){
                    j++;
                }
            }
            endPosition-=1;
            System.out.println(startPosition+";;"+endPosition);
            //设置文件上传保存路径
            String savePath="G:\\liuys\\save\\";
            File save = new File(savePath);
            if (!save.exists()){
                save.mkdir();
            }
            File saveFile = new File(savePath,tempFileName);
            RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile,"rw");

            raf.seek(startPosition);


            while (startPosition<endPosition){
                randomAccessFile.write(raf.readByte());
                startPosition=raf.getFilePointer();
            }
            tempFile.delete();
            raf.close();
            randomAccessFile.close();
            req.setAttribute("result","上传成功！");
            RequestDispatcher rd = req.getRequestDispatcher("uploadImage.html");
            rd.forward(req,resp);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
