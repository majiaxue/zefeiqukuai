package com.example.zefeiqukuai.bean;

import java.io.Serializable;

public class UppwdBean implements Serializable
{
    private String password;
    private String code;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
