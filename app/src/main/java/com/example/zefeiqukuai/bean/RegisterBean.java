package com.example.zefeiqukuai.bean;

import java.io.Serializable;

public class RegisterBean implements Serializable {
    private String parent_member;
    private String phone;
    private String password;
    private String pay_password;
    private String nick_name;
    private String code;
    private String urgent_phone;

    public String getParent_member() {
        return parent_member;
    }

    public void setParent_member(String parent_member) {
        this.parent_member = parent_member;
    }

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

    public String getPay_password() {
        return pay_password;
    }

    public void setPay_password(String pay_password) {
        this.pay_password = pay_password;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrgent_phone() {
        return urgent_phone;
    }

    public void setUrgent_phone(String urgent_phone) {
        this.urgent_phone = urgent_phone;
    }

    @Override
    public String toString() {
        return "RegisterBean{" +
                "parent_member='" + parent_member + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", pay_password='" + pay_password + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", code='" + code + '\'' +
                ", urgent_phone='" + urgent_phone + '\'' +
                '}';
    }
}
