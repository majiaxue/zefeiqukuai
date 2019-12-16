package com.example.zefeiqukuai.bean;

import java.io.Serializable;

public class UserAcountBean implements Serializable {
    /**
     * nick_name : 心机萌
     * head_image :
     * phone : 15729382421
     * uuid : P3DVD6RV
     * member : 15729382421
     * pet : 0.0000
     * income : 100.0000
     * rank_id : 3
     * rank_name : vip
     * wia : 45.0000
     * turn : 604.0000
     * property : 600.0000
     * add_income : 0.0000
     * sincerity_money : 0.0000
     */

    private String nick_name;
    private String head_image;
    private String phone;
    private String uuid;
    private String member;
    private double pet;
    private double income;
    private int rank_id;
    private String rank_name;
    private double wia;
    private double turn;
    private double property;
    private double add_income;
    private double sincerity_money;

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getHead_image() {
        return head_image;
    }

    public void setHead_image(String head_image) {
        this.head_image = head_image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public double getPet() {
        return pet;
    }

    public void setPet(double pet) {
        this.pet = pet;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public int getRank_id() {
        return rank_id;
    }

    public void setRank_id(int rank_id) {
        this.rank_id = rank_id;
    }

    public String getRank_name() {
        return rank_name;
    }

    public void setRank_name(String rank_name) {
        this.rank_name = rank_name;
    }

    public double getWia() {
        return wia;
    }

    public void setWia(double wia) {
        this.wia = wia;
    }

    public double getTurn() {
        return turn;
    }

    public void setTurn(double turn) {
        this.turn = turn;
    }

    public double getProperty() {
        return property;
    }

    public void setProperty(double property) {
        this.property = property;
    }

    public double getAdd_income() {
        return add_income;
    }

    public void setAdd_income(double add_income) {
        this.add_income = add_income;
    }

    public double getSincerity_money() {
        return sincerity_money;
    }

    public void setSincerity_money(double sincerity_money) {
        this.sincerity_money = sincerity_money;
    }
}
