package com.library.util;

/**
 * Created by Administrator on 2018/4/6 0006.
 */
public class ResultModel {
    private boolean status;
    private Object data;

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
