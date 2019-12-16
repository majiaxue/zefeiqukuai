package com.example.zefeiqukuai.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ConfigBean implements Serializable {
    /**
     * site_info : {"weixinqrcode":"/imgs/15742330961097382.jpg","kefuphone":"13103895695","gif":"/imgs/15742412321268467.jpg","music":"/files/20191120/8d84b997da43d23657676458389bfb9f.mp3"}
     * other_info : {"lock_day":"5","pay_info_num":"6"}
     * money_info : {"edit_gathering_money":"3","trends_rate":"10","dog_rate":"5","sincerity_rate":"10","sincerity_time":"30","sell_rate":"5","total_price":"10000"}
     * split_info : {"1":0,"2":"100","3":0,"4":0,"5":"100","6":"100","7":0}
     * introduce : {"introduce":"你可以在狗狗集市,注册认证并激活账户后,通过预约并匹配成功后支付押金领养你心仪的狗狗。\n一旦领养后,将区块写入唯一身份,可爱的狗狗进入\u201c休息中\u201d状态,休息中的狗狗不可以买卖,待智能合约到期后,狗狗将强制出。\n在狗狗集市场里成功领养了狗狗,不但可以得到合约的工资,还可以获得相应的狗狗币。"}
     * remite_rate : null
     * adv_info : {"id":5,"content":"","url":"6","order":"6","create_time":"2019-11-20 12:51:32","is_del":0,"type":1}
     */

    private SiteInfoBean site_info;
    private OtherInfoBean other_info;
    private MoneyInfoBean money_info;
    private SplitInfoBean split_info;
    private IntroduceBean introduce;
    private Object remite_rate;
    private AdvInfoBean adv_info;

    public SiteInfoBean getSite_info() {
        return site_info;
    }

    public void setSite_info(SiteInfoBean site_info) {
        this.site_info = site_info;
    }

    public OtherInfoBean getOther_info() {
        return other_info;
    }

    public void setOther_info(OtherInfoBean other_info) {
        this.other_info = other_info;
    }

    public MoneyInfoBean getMoney_info() {
        return money_info;
    }

    public void setMoney_info(MoneyInfoBean money_info) {
        this.money_info = money_info;
    }

    public SplitInfoBean getSplit_info() {
        return split_info;
    }

    public void setSplit_info(SplitInfoBean split_info) {
        this.split_info = split_info;
    }

    public IntroduceBean getIntroduce() {
        return introduce;
    }

    public void setIntroduce(IntroduceBean introduce) {
        this.introduce = introduce;
    }

    public Object getRemite_rate() {
        return remite_rate;
    }

    public void setRemite_rate(Object remite_rate) {
        this.remite_rate = remite_rate;
    }

    public AdvInfoBean getAdv_info() {
        return adv_info;
    }

    public void setAdv_info(AdvInfoBean adv_info) {
        this.adv_info = adv_info;
    }

    @Override
    public String toString() {
        return "ConfigBean{" +
                "site_info=" + site_info +
                ", other_info=" + other_info +
                ", money_info=" + money_info +
                ", split_info=" + split_info +
                ", introduce=" + introduce +
                ", remite_rate=" + remite_rate +
                ", adv_info=" + adv_info +
                '}';
    }

    public static class SiteInfoBean {
        /**
         * weixinqrcode : /imgs/15742330961097382.jpg
         * kefuphone : 13103895695
         * gif : /imgs/15742412321268467.jpg
         * music : /files/20191120/8d84b997da43d23657676458389bfb9f.mp3
         */

        private String weixinqrcode;
        private String kefuphone;
        private String gif;
        private String music;

        public String getWeixinqrcode() {
            return weixinqrcode;
        }

        public void setWeixinqrcode(String weixinqrcode) {
            this.weixinqrcode = weixinqrcode;
        }

        public String getKefuphone() {
            return kefuphone;
        }

        public void setKefuphone(String kefuphone) {
            this.kefuphone = kefuphone;
        }

        public String getGif() {
            return gif;
        }

        public void setGif(String gif) {
            this.gif = gif;
        }

        public String getMusic() {
            return music;
        }

        public void setMusic(String music) {
            this.music = music;
        }

        @Override
        public String toString() {
            return "SiteInfoBean{" +
                    "weixinqrcode='" + weixinqrcode + '\'' +
                    ", kefuphone='" + kefuphone + '\'' +
                    ", gif='" + gif + '\'' +
                    ", music='" + music + '\'' +
                    '}';
        }
    }

    public static class OtherInfoBean {
        /**
         * lock_day : 5
         * pay_info_num : 6
         */

        private String lock_day;
        private String pay_info_num;

        public String getLock_day() {
            return lock_day;
        }

        public void setLock_day(String lock_day) {
            this.lock_day = lock_day;
        }

        public String getPay_info_num() {
            return pay_info_num;
        }

        public void setPay_info_num(String pay_info_num) {
            this.pay_info_num = pay_info_num;
        }

        @Override
        public String toString() {
            return "OtherInfoBean{" +
                    "lock_day='" + lock_day + '\'' +
                    ", pay_info_num='" + pay_info_num + '\'' +
                    '}';
        }
    }

    public static class MoneyInfoBean {
        /**
         * edit_gathering_money : 3
         * trends_rate : 10
         * dog_rate : 5
         * sincerity_rate : 10
         * sincerity_time : 30
         * sell_rate : 5
         * total_price : 10000
         */

        private String edit_gathering_money;
        private String trends_rate;
        private String dog_rate;
        private String sincerity_rate;
        private String sincerity_time;
        private String sell_rate;
        private String total_price;

        public String getEdit_gathering_money() {
            return edit_gathering_money;
        }

        public void setEdit_gathering_money(String edit_gathering_money) {
            this.edit_gathering_money = edit_gathering_money;
        }

        public String getTrends_rate() {
            return trends_rate;
        }

        public void setTrends_rate(String trends_rate) {
            this.trends_rate = trends_rate;
        }

        public String getDog_rate() {
            return dog_rate;
        }

        public void setDog_rate(String dog_rate) {
            this.dog_rate = dog_rate;
        }

        public String getSincerity_rate() {
            return sincerity_rate;
        }

        public void setSincerity_rate(String sincerity_rate) {
            this.sincerity_rate = sincerity_rate;
        }

        public String getSincerity_time() {
            return sincerity_time;
        }

        public void setSincerity_time(String sincerity_time) {
            this.sincerity_time = sincerity_time;
        }

        public String getSell_rate() {
            return sell_rate;
        }

        public void setSell_rate(String sell_rate) {
            this.sell_rate = sell_rate;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        @Override
        public String toString() {
            return "MoneyInfoBean{" +
                    "edit_gathering_money='" + edit_gathering_money + '\'' +
                    ", trends_rate='" + trends_rate + '\'' +
                    ", dog_rate='" + dog_rate + '\'' +
                    ", sincerity_rate='" + sincerity_rate + '\'' +
                    ", sincerity_time='" + sincerity_time + '\'' +
                    ", sell_rate='" + sell_rate + '\'' +
                    ", total_price='" + total_price + '\'' +
                    '}';
        }
    }

    public static class SplitInfoBean {
        /**
         * 1 : 0
         * 2 : 100
         * 3 : 0
         * 4 : 0
         * 5 : 100
         * 6 : 100
         * 7 : 0
         */

        @SerializedName("1")
        private int _$1;
        @SerializedName("2")
        private String _$2;
        @SerializedName("3")
        private int _$3;
        @SerializedName("4")
        private int _$4;
        @SerializedName("5")
        private String _$5;
        @SerializedName("6")
        private String _$6;
        @SerializedName("7")
        private int _$7;

        public int get_$1() {
            return _$1;
        }

        public void set_$1(int _$1) {
            this._$1 = _$1;
        }

        public String get_$2() {
            return _$2;
        }

        public void set_$2(String _$2) {
            this._$2 = _$2;
        }

        public int get_$3() {
            return _$3;
        }

        public void set_$3(int _$3) {
            this._$3 = _$3;
        }

        public int get_$4() {
            return _$4;
        }

        public void set_$4(int _$4) {
            this._$4 = _$4;
        }

        public String get_$5() {
            return _$5;
        }

        public void set_$5(String _$5) {
            this._$5 = _$5;
        }

        public String get_$6() {
            return _$6;
        }

        public void set_$6(String _$6) {
            this._$6 = _$6;
        }

        public int get_$7() {
            return _$7;
        }

        public void set_$7(int _$7) {
            this._$7 = _$7;
        }

        @Override
        public String toString() {
            return "SplitInfoBean{" +
                    "_$1=" + _$1 +
                    ", _$2='" + _$2 + '\'' +
                    ", _$3=" + _$3 +
                    ", _$4=" + _$4 +
                    ", _$5='" + _$5 + '\'' +
                    ", _$6='" + _$6 + '\'' +
                    ", _$7=" + _$7 +
                    '}';
        }
    }

    public static class IntroduceBean {
        /**
         * introduce : 你可以在狗狗集市,注册认证并激活账户后,通过预约并匹配成功后支付押金领养你心仪的狗狗。
         一旦领养后,将区块写入唯一身份,可爱的狗狗进入“休息中”状态,休息中的狗狗不可以买卖,待智能合约到期后,狗狗将强制出。
         在狗狗集市场里成功领养了狗狗,不但可以得到合约的工资,还可以获得相应的狗狗币。
         */

        private String introduce;

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        @Override
        public String toString() {
            return "IntroduceBean{" +
                    "introduce='" + introduce + '\'' +
                    '}';
        }
    }

    public static class AdvInfoBean {
        /**
         * id : 5
         * content :
         * url : 6
         * order : 6
         * create_time : 2019-11-20 12:51:32
         * is_del : 0
         * type : 1
         */

        private int id;
        private String content;
        private String url;
        private String order;
        private String create_time;
        private int is_del;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "AdvInfoBean{" +
                    "id=" + id +
                    ", content='" + content + '\'' +
                    ", url='" + url + '\'' +
                    ", order='" + order + '\'' +
                    ", create_time='" + create_time + '\'' +
                    ", is_del=" + is_del +
                    ", type=" + type +
                    '}';
        }
    }
}
