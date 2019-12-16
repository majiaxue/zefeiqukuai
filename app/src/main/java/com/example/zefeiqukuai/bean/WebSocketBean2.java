package com.example.zefeiqukuai.bean;

import java.io.Serializable;
import java.util.List;


public class WebSocketBean2 implements Serializable {
    private String type;
    private String time;
    private List<String> data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WebSocketBean2{" +
                "type='" + type + '\'' +
                ", time='" + time + '\'' +
                ", data=" + data +
                '}';
    }
}
