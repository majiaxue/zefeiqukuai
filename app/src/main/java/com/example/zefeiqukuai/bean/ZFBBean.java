package com.example.zefeiqukuai.bean;

import java.io.Serializable;

public class ZFBBean implements Serializable {
    /**
     * aliname : 醒
     * alino : 13521454405
     * aliqrcode : http://192.168.0.125:8090/imgs/15758778433554971.jpg
     */

    private String aliname;
    private String alino;
    private String aliqrcode;

    public String getAliname() {
        return aliname;
    }

    public void setAliname(String aliname) {
        this.aliname = aliname;
    }

    public String getAlino() {
        return alino;
    }

    public void setAlino(String alino) {
        this.alino = alino;
    }

    public String getAliqrcode() {
        return aliqrcode;
    }

    public void setAliqrcode(String aliqrcode) {
        this.aliqrcode = aliqrcode;
    }
}
