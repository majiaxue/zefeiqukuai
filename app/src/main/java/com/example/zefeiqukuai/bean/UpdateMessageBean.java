package com.example.zefeiqukuai.bean;

import java.io.Serializable;

public class UpdateMessageBean implements Serializable
{

    /**
     * msg : 请先登录
     * errcode : 4001
     */

    private String msg;
    private int errcode;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }
}
