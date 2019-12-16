package com.example.zefeiqukuai.bean;

import java.io.Serializable;

public class PetPriceBean implements Serializable {
    /**
     * data : {"trends":110,"sincerity":"100","turn":"100"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * trends : 110
         * sincerity : 100
         * turn : 100
         */

        private String trends;
        private String sincerity;
        private String turn;

        public String getTrends() {
            return trends;
        }

        public void setTrends(String trends) {
            this.trends = trends;
        }

        public String getSincerity() {
            return sincerity;
        }

        public void setSincerity(String sincerity) {
            this.sincerity = sincerity;
        }

        public String getTurn() {
            return turn;
        }

        public void setTurn(String turn) {
            this.turn = turn;
        }
    }
}
