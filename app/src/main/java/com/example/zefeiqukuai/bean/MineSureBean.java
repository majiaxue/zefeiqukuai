package com.example.zefeiqukuai.bean;

import java.io.Serializable;

public class MineSureBean implements Serializable
{
    private String name;
    private String card;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
