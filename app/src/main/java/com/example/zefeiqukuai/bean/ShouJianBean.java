package com.example.zefeiqukuai.bean;

import java.io.Serializable;

public class ShouJianBean implements Serializable {


    /**
     * id : 15
     * uid : 223
     * title :  默默
     * content : 容
     轻拢慢捻抹复挑容
     轻拢慢捻抹复挑容
     轻拢慢捻抹复挑
     * create_time : 2019-12-03 11:35:02
     * is_del : 0
     * member : 13683679232
     */

    private int id;
    private int uid;
    private String title;
    private String content;
    private String create_time;
    private int is_del;
    private String member;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getIs_del() {
        return is_del;
    }

    public void setIs_del(int is_del) {
        this.is_del = is_del;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }
}
