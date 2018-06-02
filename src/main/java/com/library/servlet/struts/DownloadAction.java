package com.library.servlet.struts;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2018/5/2 0002.
 */
public class DownloadAction extends ActionSupport {
    public String inputPath;

    public String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }



    public InputStream getInputStream() throws IOException {
        String path=ServletActionContext.getServletContext().getRealPath("/")+"userfiles/";
        System.out.println(path+filename);
        File file=new File(path+filename);
        return FileUtils.openInputStream(file);
      // return ServletActionContext.getServletContext().getResourceAsStream(inputPath);
    }

    public String getDownloadFileName() throws UnsupportedEncodingException {
        String name = "";
        name= URLEncoder.encode("下载文件"+System.currentTimeMillis()/1000+".png","UTF-8");
        return name;
    }
}
