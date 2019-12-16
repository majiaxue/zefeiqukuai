package com.example.zefeiqukuai.bean;

import java.io.Serializable;

public class WeChatBean implements Serializable {
    /**
     * wxname : 啦
     * wxno : 13521454405
     * wxqrcode : http://192.168.0.125:8090/imgs/15758778762133576.jpg
     */

    private String wxname;
    private String wxno;
    private String wxqrcode;

    public String getWxname() {
        return wxname;
    }

    public void setWxname(String wxname) {
        this.wxname = wxname;
    }

    public String getWxno() {
        return wxno;
    }

    public void setWxno(String wxno) {
        this.wxno = wxno;
    }

    public String getWxqrcode() {
        return wxqrcode;
    }

    public void setWxqrcode(String wxqrcode) {
        this.wxqrcode = wxqrcode;
    }
}
