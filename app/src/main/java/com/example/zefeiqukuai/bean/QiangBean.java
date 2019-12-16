package com.example.zefeiqukuai.bean;

import java.io.Serializable;

public class QiangBean implements Serializable
{

    /**
     * errcode : 0
     * msg : success
     * res : {"stat":0,"data":"请等待抢购结果"}
     */

    private int errcode;
    private String msg;
    private ResBean res;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResBean getRes() {
        return res;
    }

    public void setRes(ResBean res) {
        this.res = res;
    }

    public static class ResBean {
        /**
         * stat : 0
         * data : 请等待抢购结果
         */

        private int stat;
        private String data;

        public int getStat() {
            return stat;
        }

        public void setStat(int stat) {
            this.stat = stat;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
