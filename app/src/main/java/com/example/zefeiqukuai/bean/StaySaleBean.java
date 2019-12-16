package com.example.zefeiqukuai.bean;

import java.io.Serializable;
import java.util.List;

public class StaySaleBean implements Serializable
{
    /**
     * data : [{"id":215,"uid":208,"price":"100.00","status":1,"intime":"2019-11-20 15:02:03","name":"狸花猫","day":7,"bonus_rate":"0.2500","coin_hkt":"1.0000","appoint":2,"purchase":4,"image":"/imgs/15673011887162776.jpg","coin_phd":"0.6000"}]
     * count : 1
     */

    private int count;
    private List<DataBean> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 215
         * uid : 208
         * price : 100.00
         * status : 1
         * intime : 2019-11-20 15:02:03
         * name : 狸花猫
         * day : 7
         * bonus_rate : 0.2500
         * coin_hkt : 1.0000
         * appoint : 2
         * purchase : 4
         * image : /imgs/15673011887162776.jpg
         * coin_phd : 0.6000
         */

        private int id;
        private int uid;
        private String price;
        private int status;
        private String intime;
        private String name;
        private int day;
        private double bonus_rate;
        private double coin_hkt;
        private int appoint;
        private int purchase;
        private String image;
        private String coin_phd;

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getIntime() {
            return intime;
        }

        public void setIntime(String intime) {
            this.intime = intime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
    }
}
