package com.example.zefeiqukuai.bean;

public class PayCommitBean
{
    /**
     * errcode : 0
     * msg : 提交成功,请耐心等待后台审核
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
