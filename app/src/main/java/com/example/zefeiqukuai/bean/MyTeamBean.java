package com.example.zefeiqukuai.bean;

import java.util.List;

public class MyTeamBean {


    /**
     * total_direct : 2
     * group_person_count : 7
     * gener3 : [{"id":229,"member":"15000000003","pid":228,"head_image":"","nick_name":"哈哈哈","rank_id":1,"group_person_count":1,"create_time":"2019-12-07 09:51:36","cur_level":4,"node_path":"->13683679232->15000000001->15000000002->15000000003","rank_name":"未激活会员"},{"id":233,"member":"15002225563","pid":230,"head_image":"","nick_name":"哈哈哈","rank_id":1,"group_person_count":0,"create_time":"2019-12-07 11:21:16","cur_level":4,"node_path":"->13683679232->15000000001->13622222252->15002225563","rank_name":"未激活会员"}]
     * gener2 : [{"id":228,"member":"15000000002","pid":227,"head_image":"","nick_name":"哈哈哈","rank_id":1,"group_person_count":2,"create_time":"2019-12-07 09:51:09","cur_level":3,"node_path":"->13683679232->15000000001->15000000002","rank_name":"未激活会员"},{"id":230,"member":"13622222252","pid":227,"head_image":"","nick_name":"哈哈哈","rank_id":1,"group_person_count":1,"create_time":"2019-12-07 10:38:38","cur_level":3,"node_path":"->13683679232->15000000001->13622222252","rank_name":"未激活会员"}]
     * gener1 : [{"id":227,"member":"15000000001","pid":223,"head_image":"","nick_name":"哈哈哈","rank_id":1,"group_person_count":5,"create_time":"2019-12-07 09:48:14","cur_level":2,"node_path":"->13683679232->15000000001","rank_name":"未激活会员"},{"id":231,"member":"12633333332","pid":223,"head_image":"","nick_name":"哈哈哈","rank_id":1,"group_person_count":0,"create_time":"2019-12-07 10:50:11","cur_level":2,"node_path":"->13683679232->12633333332","rank_name":"未激活会员"}]
     * today_count : 6
     */

    private int total_direct;
    private int group_person_count;
    private int today_count;
    private List<Gener3Bean> gener3;
    private List<Gener2Bean> gener2;
    private List<Gener1Bean> gener1;

    public int getTotal_direct() {
        return total_direct;
    }

    public void setTotal_direct(int total_direct) {
        this.total_direct = total_direct;
    }

    public int getGroup_person_count() {
        return group_person_count;
    }

    public void setGroup_person_count(int group_person_count) {
        this.group_person_count = group_person_count;
    }

    public int getToday_count() {
        return today_count;
    }

    public void setToday_count(int today_count) {
        this.today_count = today_count;
    }

    public List<Gener3Bean> getGener3() {
        return gener3;
    }

    public void setGener3(List<Gener3Bean> gener3) {
        this.gener3 = gener3;
    }

    public List<Gener2Bean> getGener2() {
        return gener2;
    }

    public void setGener2(List<Gener2Bean> gener2) {
        this.gener2 = gener2;
    }

    public List<Gener1Bean> getGener1() {
        return gener1;
    }

    public void setGener1(List<Gener1Bean> gener1) {
        this.gener1 = gener1;
    }

    public static class Gener3Bean {
        /**
         * id : 229
         * member : 15000000003
         * pid : 228
         * head_image :
         * nick_name : 哈哈哈
         * rank_id : 1
         * group_person_count : 1
         * create_time : 2019-12-07 09:51:36
         * cur_level : 4
         * node_path : ->13683679232->15000000001->15000000002->15000000003
         * rank_name : 未激活会员
         */

        private int id;
        private String member;
        private int pid;
        private String head_image;
        private String nick_name;
        private int rank_id;
        private int group_person_count;
        private String create_time;
        private int cur_level;
        private String node_path;
        private String rank_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMember() {
            return member;
        }

        public void setMember(String member) {
            this.member = member;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

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

        public int getRank_id() {
            return rank_id;
        }

        public void setRank_id(int rank_id) {
            this.rank_id = rank_id;
        }

        public int getGroup_person_count() {
            return group_person_count;
        }

        public void setGroup_person_count(int group_person_count) {
            this.group_person_count = group_person_count;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getCur_level() {
            return cur_level;
        }

        public void setCur_level(int cur_level) {
            this.cur_level = cur_level;
        }

        public String getNode_path() {
            return node_path;
        }

        public void setNode_path(String node_path) {
            this.node_path = node_path;
        }

        public String getRank_name() {
            return rank_name;
        }

        public void setRank_name(String rank_name) {
            this.rank_name = rank_name;
        }
    }

    public static class Gener2Bean {
        /**
         * id : 228
         * member : 15000000002
         * pid : 227
         * head_image :
         * nick_name : 哈哈哈
         * rank_id : 1
         * group_person_count : 2
         * create_time : 2019-12-07 09:51:09
         * cur_level : 3
         * node_path : ->13683679232->15000000001->15000000002
         * rank_name : 未激活会员
         */

        private int id;
        private String member;
        private int pid;
        private String head_image;
        private String nick_name;
        private int rank_id;
        private int group_person_count;
        private String create_time;
        private int cur_level;
        private String node_path;
        private String rank_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMember() {
            return member;
        }

        public void setMember(String member) {
            this.member = member;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

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

        public int getRank_id() {
            return rank_id;
        }

        public void setRank_id(int rank_id) {
            this.rank_id = rank_id;
        }

        public int getGroup_person_count() {
            return group_person_count;
        }

        public void setGroup_person_count(int group_person_count) {
            this.group_person_count = group_person_count;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getCur_level() {
            return cur_level;
        }

        public void setCur_level(int cur_level) {
            this.cur_level = cur_level;
        }

        public String getNode_path() {
            return node_path;
        }

        public void setNode_path(String node_path) {
            this.node_path = node_path;
        }

        public String getRank_name() {
            return rank_name;
        }

        public void setRank_name(String rank_name) {
            this.rank_name = rank_name;
        }
    }

    public static class Gener1Bean {
        /**
         * id : 227
         * member : 15000000001
         * pid : 223
         * head_image :
         * nick_name : 哈哈哈
         * rank_id : 1
         * group_person_count : 5
         * create_time : 2019-12-07 09:48:14
         * cur_level : 2
         * node_path : ->13683679232->15000000001
         * rank_name : 未激活会员
         */

        private int id;
        private String member;
        private int pid;
        private String head_image;
        private String nick_name;
        private int rank_id;
        private int group_person_count;
        private String create_time;
        private int cur_level;
        private String node_path;
        private String rank_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMember() {
            return member;
        }

        public void setMember(String member) {
            this.member = member;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

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

        public int getRank_id() {
            return rank_id;
        }

        public void setRank_id(int rank_id) {
            this.rank_id = rank_id;
        }

        public int getGroup_person_count() {
            return group_person_count;
        }

        public void setGroup_person_count(int group_person_count) {
            this.group_person_count = group_person_count;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getCur_level() {
            return cur_level;
        }

        public void setCur_level(int cur_level) {
            this.cur_level = cur_level;
        }

        public String getNode_path() {
            return node_path;
        }

        public void setNode_path(String node_path) {
            this.node_path = node_path;
        }

        public String getRank_name() {
            return rank_name;
        }

        public void setRank_name(String rank_name) {
            this.rank_name = rank_name;
        }
    }
}
