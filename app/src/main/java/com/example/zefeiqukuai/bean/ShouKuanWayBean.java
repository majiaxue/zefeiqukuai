package com.example.zefeiqukuai.bean;

import java.io.Serializable;

public class ShouKuanWayBean implements Serializable{
    /**
     * errcode : 0
     * msg : 修改成功
     */

    private int errcode;
    private String msg;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
