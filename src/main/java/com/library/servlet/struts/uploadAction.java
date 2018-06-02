package com.library.servlet.struts;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2018/5/2 0002.
 */
public class uploadAction extends ActionSupport{
    private List<File> upload;
    private List<String> uploadContentType;
    private List<String> uploadFileName;
    private String result;

    public List<File> getUpload() {
        return upload;
    }

    public void setUpload(List<File> upload) {
        this.upload = upload;
    }

    public List<String> getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(List<String> uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public List<String> getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(List<String> uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String execute() throws Exception {
        String path = ServletActionContext.getServletContext().getRealPath("/img/ImageUpload/");
        System.out.println(path);
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }
        for (int i = 0; i <upload.size() ; i++) {
            FileUtils.copyFile(upload.get(i),new File(file,uploadFileName.get(i)));
        }

        result = "上传成功！";
        return SUCCESS;
    }
}
