package com.example.zefeiqukuai.bean;

import java.io.Serializable;
import java.util.List;

public class YuYueBean implements Serializable {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * create_time : 2019-11-22 09:45:31
         * id : 206
         * dog_type : 1
         * name : 加菲猫
         * start_price : 901
         * end_price : 2500
         * bonus_rate : 0.1200
         * day : 5
         * coin_hkt : 5.0000
         * appoint : 10
         * purchase : 20
         * image : /imgs/15673011979738314.jpg
         * coin_phd : 0.4000
         */

        private String create_time;
        private int id;
        private int dog_type;
        private String name;
        private double start_price;
        private double end_price;
        private double bonus_rate;
        private int day;
        private double coin_hkt;
        private int appoint;
        private int purchase;
        private String image;
        private String coin_phd;

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDog_type() {
            return dog_type;
        }

        public void setDog_type(int dog_type) {
            this.dog_type = dog_type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getStart_price() {
            return start_price;
        }

        public void setStart_price(double start_price) {
            this.start_price = start_price;
        }

        public double getEnd_price() {
            return end_price;
        }

        public void setEnd_price(double end_price) {
            this.end_price = end_price;
        }

        public double getBonus_rate() {
            return bonus_rate;
        }

        public void setBonus_rate(double bonus_rate) {
            this.bonus_rate = bonus_rate;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
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
