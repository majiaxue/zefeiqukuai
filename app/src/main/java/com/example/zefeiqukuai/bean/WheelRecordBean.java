package com.example.zefeiqukuai.bean;

import java.util.List;

public class WheelRecordBean {
    /**
     * data : [{"id":75,"user_id":221,"status":1,"rate":"3.00","result":"3倍","time":"2019-12-07 17:41:22","user":{"member":"135****4405","nick_name":"yy"}},{"id":72,"user_id":221,"status":1,"rate":"3.00","result":"3倍","time":"2019-12-07 17:41:20","user":{"member":"135****4405","nick_name":"yy"}},{"id":70,"user_id":221,"status":1,"rate":"3.00","result":"3倍","time":"2019-12-07 17:41:20","user":{"member":"135****4405","nick_name":"yy"}},{"id":68,"user_id":221,"status":1,"rate":"3.00","result":"3倍","time":"2019-12-07 17:41:19","user":{"member":"135****4405","nick_name":"yy"}},{"id":67,"user_id":221,"status":1,"rate":"3.00","result":"3倍","time":"2019-12-07 17:41:22","user":{"member":"135****4405","nick_name":"yy"}},{"id":64,"user_id":221,"status":1,"rate":"3.00","result":"3倍","time":"2019-12-07 17:41:18","user":{"member":"135****4405","nick_name":"yy"}},{"id":62,"user_id":221,"status":1,"rate":"3.00","result":"3倍","time":"2019-12-07 17:41:18","user":{"member":"135****4405","nick_name":"yy"}},{"id":60,"user_id":221,"status":1,"rate":"3.00","result":"3倍","time":"2019-12-07 17:41:17","user":{"member":"135****4405","nick_name":"yy"}},{"id":57,"user_id":221,"status":1,"rate":"1.50","result":"1.5倍","time":"2019-12-07 17:41:23","user":{"member":"135****4405","nick_name":"yy"}},{"id":40,"user_id":221,"status":1,"rate":"2.00","result":null,"time":"2019-12-07 17:41:26","user":{"member":"135****4405","nick_name":"yy"}},{"id":36,"user_id":221,"status":1,"rate":"1.50","result":null,"time":"2019-12-07 17:41:26","user":{"member":"135****4405","nick_name":"yy"}},{"id":34,"user_id":221,"status":1,"rate":"3.00","result":null,"time":"2019-12-07 17:41:28","user":{"member":"135****4405","nick_name":"yy"}},{"id":26,"user_id":221,"status":1,"rate":"5.00","result":null,"time":"2019-12-07 17:41:29","user":{"member":"135****4405","nick_name":"yy"}},{"id":24,"user_id":221,"status":1,"rate":"5.00","result":null,"time":"2019-12-07 17:41:31","user":{"member":"135****4405","nick_name":"yy"}},{"id":22,"user_id":221,"status":1,"rate":"2.00","result":null,"time":"2019-12-07 17:41:30","user":{"member":"135****4405","nick_name":"yy"}},{"id":18,"user_id":221,"status":1,"rate":"2.00","result":null,"time":"2019-12-07 17:41:34","user":{"member":"135****4405","nick_name":"yy"}},{"id":16,"user_id":221,"status":1,"rate":"3.00","result":null,"time":"2019-12-07 17:41:36","user":{"member":"135****4405","nick_name":"yy"}},{"id":14,"user_id":221,"status":1,"rate":"3.00","result":null,"time":"2019-12-07 17:41:33","user":{"member":"135****4405","nick_name":"yy"}},{"id":12,"user_id":221,"status":1,"rate":"2.00","result":null,"time":"2019-12-07 17:41:32","user":{"member":"135****4405","nick_name":"yy"}},{"id":5,"user_id":221,"status":1,"rate":"2.00","result":null,"time":"2019-12-07 17:41:37","user":{"member":"135****4405","nick_name":"yy"}}]
     * count : 21
     * page : 0
     * pagesize : 20
     */

    private int count;
    private int page;
    private int pagesize;
    private List<DataBean> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 75
         * user_id : 221
         * status : 1
         * rate : 3.00
         * result : 3倍
         * time : 2019-12-07 17:41:22
         * user : {"member":"135****4405","nick_name":"yy"}
         */

        private int id;
        private int user_id;
        private int status;
        private String rate;
        private String result;
        private String time;
        private UserBean user;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * member : 135****4405
             * nick_name : yy
             */

            private String member;
            private String nick_name;

            public String getMember() {
                return member;
            }

            public void setMember(String member) {
                this.member = member;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }
        }
    }
}
