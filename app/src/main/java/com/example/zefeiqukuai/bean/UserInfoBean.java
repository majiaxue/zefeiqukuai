package com.example.zefeiqukuai.bean;

import java.io.Serializable;

public class UserInfoBean implements Serializable
{

    /**
     * head_image :
     * nick_name : ying
     * member : 13521454405
     * uuid : JJ5RHLAP
     * parent_nick_name : 白羽
     * parent_member : 15729382422
     * parent_name : null
     * rank_name : 未激活会员
     * name":"赵迎弟",
     * "card":"342221199705033042"
     */

    private String head_image;
    private String nick_name;
    private String member;
    private String uuid;
    private String parent_nick_name;
    private String parent_member;
    private String parent_name;
    private String name;
    private String card;
    private String rank_name;


    public String getHead_image() {
        return head_image;
    }

    public void setHead_image(String head_image) {
        this.head_image = head_image;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getParent_nick_name() {
        return parent_nick_name;
    }

    public void setParent_nick_name(String parent_nick_name) {
        this.parent_nick_name = parent_nick_name;
    }

    public String getParent_member() {
        return parent_member;
    }

    public void setParent_member(String parent_member) {
        this.parent_member = parent_member;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getRank_name() {
        return rank_name;
    }

    public void setRank_name(String rank_name) {
        this.rank_name = rank_name;
    }
}
