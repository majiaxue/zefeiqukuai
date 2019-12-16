package com.example.zefeiqukuai.bean;

import java.io.Serializable;

public class VipBean implements Serializable
{

    /**
     * name : 未激活会员
     * remark : 未激活会员
     * floor : null
     * rate : null
     */

    private String name;
    private String remark;
    private String floor;
    private String rate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
