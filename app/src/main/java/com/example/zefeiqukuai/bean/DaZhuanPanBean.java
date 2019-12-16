package com.example.zefeiqukuai.bean;

import java.io.Serializable;
import java.util.List;

public class DaZhuanPanBean implements Serializable
{

    /**
     * id : 1
     * name : 幸运大转盘
     * cost : 30.0000
     * start_time : 2019-11-25 00:00:00
     * end_time : 2019-11-30 00:00:00
     * activity_explain : 一、hello
     二、world
     * everyday : 1000
     * recharge : 200.00
     * status : 1
     * level1 : {"id":"1","rate":"1.5","text":"1.5倍","image":"/imgs/15746759828328328.jpg","chance":"22"}
     * level2 : {"id":"2","rate":"2","text":"2倍","image":"/imgs/15746759828328328.jpg","chance":"15"}
     * level3 : {"id":"3","rate":"3","text":"3倍","image":"/imgs/15746759828328328.jpg","chance":"10"}
     * level4 : {"id":"4","rate":"0","text":"谢谢惠顾","image":"/imgs/15746759828328328.jpg","chance":"20"}
     * level5 : {"id":"5","rate":"3","text":"3倍","image":"/imgs/15746759828328328.jpg","chance":"10"}
     * level6 : {"id":"6","rate":"4","text":"4倍","image":"/imgs/15746759828328328.jpg","chance":"8"}
     * level7 : {"id":"7","rate":"5","text":"5倍","image":"/imgs/15746768474722912.jpg","chance":"5"}
     * level8 : {"id":"8","rate":"0","text":"谢谢惠顾","image":"/imgs/15746767578245934.jpg","chance":"10"}
     * time : 2019-11-26 14:37:41
     * list : [{"id":"1","rate":"1.5","text":"1.5倍","image":"/imgs/15746759828328328.jpg","chance":"22"},{"id":"2","rate":"2","text":"2倍","image":"/imgs/15746759828328328.jpg","chance":"15"},{"id":"3","rate":"3","text":"3倍","image":"/imgs/15746759828328328.jpg","chance":"10"},{"id":"4","rate":"0","text":"谢谢惠顾","image":"/imgs/15746759828328328.jpg","chance":"20"},{"id":"5","rate":"3","text":"3倍","image":"/imgs/15746759828328328.jpg","chance":"10"},{"id":"6","rate":"4","text":"4倍","image":"/imgs/15746759828328328.jpg","chance":"8"},{"id":"7","rate":"5","text":"5倍","image":"/imgs/15746768474722912.jpg","chance":"5"},{"id":"8","rate":"0","text":"谢谢惠顾","image":"/imgs/15746767578245934.jpg","chance":"10"}]
     */

    private int id;
    private String name;
    private double cost;
    private String start_time;
    private String end_time;
    private String activity_explain;
    private int everyday;
    private String recharge;
    private int status;
    private Level1Bean level1;
    private Level2Bean level2;
    private Level3Bean level3;
    private Level4Bean level4;
    private Level5Bean level5;
    private Level6Bean level6;
    private Level7Bean level7;
    private Level8Bean level8;
    private String time;
    private List<ListBean> list;

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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
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

    public String getActivity_explain() {
        return activity_explain;
    }

    public void setActivity_explain(String activity_explain) {
        this.activity_explain = activity_explain;
    }

    public int getEveryday() {
        return everyday;
    }

    public void setEveryday(int everyday) {
        this.everyday = everyday;
    }

    public String getRecharge() {
        return recharge;
    }

    public void setRecharge(String recharge) {
        this.recharge = recharge;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Level1Bean getLevel1() {
        return level1;
    }

    public void setLevel1(Level1Bean level1) {
        this.level1 = level1;
    }

    public Level2Bean getLevel2() {
        return level2;
    }

    public void setLevel2(Level2Bean level2) {
        this.level2 = level2;
    }

    public Level3Bean getLevel3() {
        return level3;
    }

    public void setLevel3(Level3Bean level3) {
        this.level3 = level3;
    }

    public Level4Bean getLevel4() {
        return level4;
    }

    public void setLevel4(Level4Bean level4) {
        this.level4 = level4;
    }

    public Level5Bean getLevel5() {
        return level5;
    }

    public void setLevel5(Level5Bean level5) {
        this.level5 = level5;
    }

    public Level6Bean getLevel6() {
        return level6;
    }

    public void setLevel6(Level6Bean level6) {
        this.level6 = level6;
    }

    public Level7Bean getLevel7() {
        return level7;
    }

    public void setLevel7(Level7Bean level7) {
        this.level7 = level7;
    }

    public Level8Bean getLevel8() {
        return level8;
    }

    public void setLevel8(Level8Bean level8) {
        this.level8 = level8;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class Level1Bean {
        /**
         * id : 1
         * rate : 1.5
         * text : 1.5倍
         * image : /imgs/15746759828328328.jpg
         * chance : 22
         */

        private String id;
        private String rate;
        private String text;
        private String image;
        private String chance;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getChance() {
            return chance;
        }

        public void setChance(String chance) {
            this.chance = chance;
        }
    }

    public static class Level2Bean {
        /**
         * id : 2
         * rate : 2
         * text : 2倍
         * image : /imgs/15746759828328328.jpg
         * chance : 15
         */

        private String id;
        private String rate;
        private String text;
        private String image;
        private String chance;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getChance() {
            return chance;
        }

        public void setChance(String chance) {
            this.chance = chance;
        }
    }

    public static class Level3Bean {
        /**
         * id : 3
         * rate : 3
         * text : 3倍
         * image : /imgs/15746759828328328.jpg
         * chance : 10
         */

        private String id;
        private String rate;
        private String text;
        private String image;
        private String chance;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getChance() {
            return chance;
        }

        public void setChance(String chance) {
            this.chance = chance;
        }
    }

    public static class Level4Bean {
        /**
         * id : 4
         * rate : 0
         * text : 谢谢惠顾
         * image : /imgs/15746759828328328.jpg
         * chance : 20
         */

        private String id;
        private String rate;
        private String text;
        private String image;
        private String chance;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getChance() {
            return chance;
        }

        public void setChance(String chance) {
            this.chance = chance;
        }
    }

    public static class Level5Bean {
        /**
         * id : 5
         * rate : 3
         * text : 3倍
         * image : /imgs/15746759828328328.jpg
         * chance : 10
         */

        private String id;
        private String rate;
        private String text;
        private String image;
        private String chance;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getChance() {
            return chance;
        }

        public void setChance(String chance) {
            this.chance = chance;
        }
    }

    public static class Level6Bean {
        /**
         * id : 6
         * rate : 4
         * text : 4倍
         * image : /imgs/15746759828328328.jpg
         * chance : 8
         */

        private String id;
        private String rate;
        private String text;
        private String image;
        private String chance;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getChance() {
            return chance;
        }

        public void setChance(String chance) {
            this.chance = chance;
        }
    }

    public static class Level7Bean {
        /**
         * id : 7
         * rate : 5
         * text : 5倍
         * image : /imgs/15746768474722912.jpg
         * chance : 5
         */

        private String id;
        private String rate;
        private String text;
        private String image;
        private String chance;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getChance() {
            return chance;
        }

        public void setChance(String chance) {
            this.chance = chance;
        }
    }

    public static class Level8Bean {
        /**
         * id : 8
         * rate : 0
         * text : 谢谢惠顾
         * image : /imgs/15746767578245934.jpg
         * chance : 10
         */

        private String id;
        private String rate;
        private String text;
        private String image;
        private String chance;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getChance() {
            return chance;
        }

        public void setChance(String chance) {
            this.chance = chance;
        }
    }

    public static class ListBean {
        /**
         * id : 1
         * rate : 1.5
         * text : 1.5倍
         * image : /imgs/15746759828328328.jpg
         * chance : 22
         */

        private String id;
        private String rate;
        private String text;
        private String image;
        private String chance;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getChance() {
            return chance;
        }

        public void setChance(String chance) {
            this.chance = chance;
        }
    }
}
