package com.example.zefeiqukuai.bean;

import java.io.Serializable;

public class ForgetPasswordBean implements Serializable
{
    private String phone ;
    private String password ;
    private String code ;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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
