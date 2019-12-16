package com.example.zefeiqukuai.bean;

import java.io.Serializable;

public class RegBean implements Serializable {

    /**
     * errcode : 5000
     * msg : 当前手机号已经注册,请更换手机号
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
