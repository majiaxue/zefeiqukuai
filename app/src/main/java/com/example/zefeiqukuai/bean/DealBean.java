package com.example.zefeiqukuai.bean;

import java.io.Serializable;
import java.util.List;

public class DealBean implements Serializable
{


    /**
     * data : [{"id":49,"buyuid":237,"price":100,"status":2,"addtime":"2019-12-11 14:39:21","paytime":"2019-12-11 14:49:27","confirmtime":"2019-12-11 14:52:05","start_time":"10:36","end_time":"23:00","name":"狸花猫","bonus_rate":"0.2500","day":7,"coin_hkt":"1.0000","appoint":2,"purchase":4,"image":"/imgs/20191205/ce9f4f423578e4f98561a9fee629022d.gif","coin_phd":"0.6000"},{"id":50,"buyuid":237,"price":100,"status":2,"addtime":"2019-12-11 18:10:37","paytime":"2019-12-11 18:11:19","confirmtime":"2019-12-11 18:22:53","start_time":"18:10","end_time":"20:00","name":"森林猫","bonus_rate":"0.1250","day":5,"coin_hkt":"4.0000","appoint":10,"purchase":20,"image":"/imgs/20191205/55238ec71484627d58ed8ce40966e839.gif","coin_phd":"0.6000"},{"id":52,"buyuid":237,"price":100,"status":2,"addtime":"2019-12-12 10:36:07","paytime":"2019-12-12 10:37:38","confirmtime":"2019-12-12 11:30:30","start_time":"10:36","end_time":"23:00","name":"狸花猫","bonus_rate":"0.2500","day":7,"coin_hkt":"1.0000","appoint":2,"purchase":4,"image":"/imgs/20191205/ce9f4f423578e4f98561a9fee629022d.gif","coin_phd":"0.6000"}]
     * count : 3
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

    public static class DataBean implements Serializable{
        /**
         * id : 49
         * buyuid : 237
         * price : 100
         * status : 2
         * addtime : 2019-12-11 14:39:21
         * paytime : 2019-12-11 14:49:27
         * confirmtime : 2019-12-11 14:52:05
         * start_time : 10:36
         * end_time : 23:00
         * name : 狸花猫
         * bonus_rate : 0.2500
         * day : 7
         * coin_hkt : 1.0000
         * appoint : 2
         * purchase : 4
         * image : /imgs/20191205/ce9f4f423578e4f98561a9fee629022d.gif
         * coin_phd : 0.6000
         */

        private int id;
        private int buyuid;
        private int price;
        private int status;
        private String addtime;
        private String paytime;
        private String confirmtime;
        private String start_time;
        private String end_time;
        private String name;
        private double bonus_rate;
        private int day;
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

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getPaytime() {
            return paytime;
        }

        public void setPaytime(String paytime) {
            this.paytime = paytime;
        }

        public String getConfirmtime() {
            return confirmtime;
        }

        public void setConfirmtime(String confirmtime) {
            this.confirmtime = confirmtime;
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

        public String getCoin_phd() {
            return coin_phd;
        }

        public void setCoin_phd(String coin_phd) {
            this.coin_phd = coin_phd;
        }
    }
}
