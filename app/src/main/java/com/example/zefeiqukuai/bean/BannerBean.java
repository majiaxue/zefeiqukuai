package com.example.zefeiqukuai.bean;

import java.io.Serializable;

public class BannerBean implements Serializable {
    /**
     * id : 8
     * image : /imgs/15752777572995724.jpg
     * url : 1
     * order : 1
     * create_time : 2019-10-15 17:22:16
     * is_del : 0
     */

    private int id;
    private String image;
    private String url;
    private String order;
    private String create_time;
    private int is_del;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
