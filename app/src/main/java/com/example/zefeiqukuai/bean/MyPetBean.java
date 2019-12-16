package com.example.zefeiqukuai.bean;

import java.io.Serializable;

public class MyPetBean implements Serializable {
    /**
     * id : 1
     * name : 加菲猫
     * start_price : 100
     * end_price : 300
     * cost : 0.00
     * day : 5
     * bonus_rate : 0.1200
     * coin_hkt : 5.0000
     * appoint : 10
     * purchase : 20
     * start_time : 14:25:22
     * end_time : 21:51:00
     * create_time : 2019-12-11 14:23:02
     * image : /imgs/20191205/d33d7a4c6fec4776dfefa524a7223d1c.gif
     * coin_phd : 0.4000
     * convert_num : 49
     */

    private int id;
    private String name;
    private int start_price;
    private int end_price;
    private String cost;
    private int day;
    private double bonus_rate;
    private double coin_hkt;
    private int appoint;
    private int purchase;
    private String start_time;
    private String end_time;
    private String create_time;
    private String image;
    private String coin_phd;
    private int convert_num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStart_price() {
        return start_price;
    }

    public void setStart_price(int start_price) {
        this.start_price = start_price;
    }

    public int getEnd_price() {
        return end_price;
    }

    public void setEnd_price(int end_price) {
        this.end_price = end_price;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getBonus_rate() {
        return bonus_rate;
    }

    public void setBonus_rate(double bonus_rate) {
        this.bonus_rate = bonus_rate;
    }

    public double getCoin_hkt() {
        return coin_hkt;
    }

    public void setCoin_hkt(double coin_hkt) {
        this.coin_hkt = coin_hkt;
    }

    public int getAppoint() {
        return appoint;
    }

    public void setAppoint(int appoint) {
        this.appoint = appoint;
    }

    public int getPurchase() {
        return purchase;
    }

    public void setPurchase(int purchase) {
        this.purchase = purchase;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCoin_phd() {
        return coin_phd;
    }

    public void setCoin_phd(String coin_phd) {
        this.coin_phd = coin_phd;
    }

    public int getConvert_num() {
        return convert_num;
    }

    public void setConvert_num(int convert_num) {
        this.convert_num = convert_num;
    }
}
