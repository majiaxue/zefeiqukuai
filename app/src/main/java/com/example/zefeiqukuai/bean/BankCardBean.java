package com.example.zefeiqukuai.bean;

import java.io.Serializable;

public class BankCardBean implements Serializable  {
    /**
     * bankname : 农业银行
     * branch : 金山区
     * cardname : 金钱
     * cardno : 352221456687453655
     */

    private String bankname;
    private String branch;
    private String cardname;
    private String cardno;

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }
}
