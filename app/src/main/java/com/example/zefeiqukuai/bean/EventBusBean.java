package com.example.zefeiqukuai.bean;

public class EventBusBean {
    private String type;
    private String client_id;

    public EventBusBean(String type, String client_id) {
        this.type = type;
        this.client_id = client_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    @Override
    public String toString() {
        return "EventBusBean{" +
                "type='" + type + '\'' +
                ", content='" + client_id + '\'' +
                '}';
    }
}
