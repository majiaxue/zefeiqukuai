package com.example.zefeiqukuai.bean;

import java.util.List;

public class YiLingYangBean {
    /**
     * data : [{"id":217,"uid":208,"image":"/imgs/15673011979738314.jpg","price":"100.00","status":0,"adopt_time":"2019-11-22 11:00:21","name":"加菲猫","day":5,"bonus_rate":"0.1200","coin_hkt":"5.0000","appoint":10,"purchase":20,"feed_time":null,"is_feed":0}]
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
         * id : 217
         * uid : 208
         * image : /imgs/15673011979738314.jpg
         * price : 100.00
         * status : 0
         * adopt_time : 2019-11-22 11:00:21
         * name : 加菲猫
         * day : 5
         * bonus_rate : 0.1200
         * coin_hkt : 5.0000
         * appoint : 10
         * purchase : 20
         * feed_time : null
         * is_feed : 0
         */

        private int id;
        private int uid;
        private String image;
        private String price;
        private int status;
        private String adopt_time;
        private String name;
        private int day;
        private double bonus_rate;
        private double coin_hkt;
        private int appoint;
        private int purchase;
        private Object feed_time;
        private int is_feed;

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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

        public String getAdopt_time() {
            return adopt_time;
        }

        public void setAdopt_time(String adopt_time) {
            this.adopt_time = adopt_time;
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

        public Object getFeed_time() {
            return feed_time;
        }

        public void setFeed_time(Object feed_time) {
            this.feed_time = feed_time;
        }

        public int getIs_feed() {
            return is_feed;
        }

        public void setIs_feed(int is_feed) {
            this.is_feed = is_feed;
        }
    }
}
