package com.example.zefeiqukuai.bean;

import java.io.Serializable;

public class GetNickNameBean implements Serializable
{
    /**
     * id : 208
     * nick_name : sfsddsfsd
     */

    private int id;
    private String nick_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }
}
