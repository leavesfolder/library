package com.library.servlet.ckediter;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by Administrator on 2018/5/4 0004.
 */
public class CKfinder extends ActionSupport {
    private String editor;

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    @Override
    public String execute() throws Exception {
        int begin = editor.indexOf("<a");
        while (begin>-1){

            int endIndex = editor.indexOf(">",begin+1)+1;
            String stra = editor.substring(begin,endIndex);
            String beginStr = editor.substring(0,begin);
            String endStr = editor.substring(endIndex);
            String filename = stra.substring(stra.lastIndexOf("userfiles/")+10,stra.lastIndexOf(">")-1);
            System.out.println(filename);
            String replace = "<a href='download.action?filename="+filename+"'>";
            editor=beginStr+replace+endStr;
            begin = editor.indexOf("<a",endIndex);
            System.out.println(editor);
        }

        return SUCCESS;
    }
}
