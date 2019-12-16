package com.example.zefeiqukuai.bean;

import java.util.List;

public class JiaoYiBean {

    /**
     * total_account : 7815.8000
     * data : [{"bill_type":"后台充值","remark":"后台管理员给会员15729382421充值","account":"500.0000","create_time":"2019-11-25 17:52:50","income":"1"},{"bill_type":"宠物兑换","remark":"会员15729382421兑换宠物扣费","account":"991.1000","create_time":"2019-11-25 11:30:35","income":"0"}]
     */

    private Double total_account;
    private List<DataBean> data;

    public Double getTotal_account() {
        return total_account;
    }

    public void setTotal_account(Double total_account) {
        this.total_account = total_account;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JiaoYiBean{" +
                "total_account=" + total_account +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * bill_type : 后台充值
         * remark : 后台管理员给会员15729382421充值
         * account : 500.0000
         * create_time : 2019-11-25 17:52:50
         * income : 1
         */

        private String bill_type;
        private String remark;
        private Double account;
        private String create_time;
        private int income;

        public String getBill_type() {
            return bill_type;
        }

        public void setBill_type(String bill_type) {
            this.bill_type = bill_type;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Double getAccount() {
            return account;
        }

        public void setAccount(Double account) {
            this.account = account;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getIncome() {
            return income;
        }

        public void setIncome(int income) {
            this.income = income;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "bill_type='" + bill_type + '\'' +
                    ", remark='" + remark + '\'' +
                    ", account=" + account +
                    ", create_time='" + create_time + '\'' +
                    ", income=" + income +
                    '}';
        }
    }
}
