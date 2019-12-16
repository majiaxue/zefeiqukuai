package com.example.zefeiqukuai.bean;

public class ChouJiangResultBean {

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

    @Override
    public String toString() {
        return "ChouJiangResultBean{" +
                "id='" + id + '\'' +
                ", rate='" + rate + '\'' +
                ", text='" + text + '\'' +
                ", image='" + image + '\'' +
                ", chance='" + chance + '\'' +
                '}';
    }
}
