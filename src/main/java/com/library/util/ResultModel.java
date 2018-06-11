package com.library.util;

/**
 * Created by Administrator on 2018/4/6 0006.
 */
public class ResultModel {
    private boolean status;
    private Object data;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultModel(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResultModel(boolean status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public ResultModel(Object data) {
        this.data = data;
    }

    public ResultModel(boolean status) {
        this.status = status;
    }

    public ResultModel(boolean status, Object data) {
        this.status = status;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
