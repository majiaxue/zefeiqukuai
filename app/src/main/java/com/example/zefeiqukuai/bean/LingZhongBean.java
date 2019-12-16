package com.example.zefeiqukuai.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class LingZhongBean implements Serializable {

    /**
     * data : [{"id":44,"buyuid":221,"price":200,"status":1,"pay_type":1,"addtime":"2019-12-11 11:01:31","start_time":"13:12","end_time":"21:51","name":"加菲猫","bonus_rate":"0.1200","day":5,"coin_hkt":"5.0000","appoint":10,"purchase":20,"image":"/imgs/20191205/d33d7a4c6fec4776dfefa524a7223d1c.gif"},{"id":46,"buyuid":221,"price":400,"status":1,"pay_type":1,"addtime":"2019-12-11 13:22:26","start_time":"13:16","end_time":"23:00","name":"狸花猫","bonus_rate":"0.2500","day":7,"coin_hkt":"1.0000","appoint":2,"purchase":4,"image":"/imgs/20191205/ce9f4f423578e4f98561a9fee629022d.gif"}]
     * count : 2
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

    public static class DataBean implements Serializable {
        /**
         * id : 44
         * buyuid : 221
         * price : 200
         * status : 1
         * pay_type : 1
         * addtime : 2019-12-11 11:01:31
         * start_time : 13:12
         * end_time : 21:51
         * name : 加菲猫
         * bonus_rate : 0.1200
         * day : 5
         * coin_hkt : 5.0000
         * appoint : 10
         * purchase : 20
         * image : /imgs/20191205/d33d7a4c6fec4776dfefa524a7223d1c.gif
         */

        private int id;
        private int buyuid;
        private int price;
        private int status;
        private String pay_type;
        private String addtime;
        private String start_time;
        private String end_time;
        private String name;
        private double bonus_rate;
        private int day;
        private double coin_hkt;
        private int appoint;
        private int purchase;
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getBuyuid() {
            return buyuid;
        }

        public void setBuyuid(int buyuid) {
            this.buyuid = buyuid;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
    }
}
