package com.example.zefeiqukuai.bean;

public class MusicBean {

    /**
     * errcode : 0
     * msg : success
     * res : {"music":"/files/20191120/8d84b997da43d23657676458389bfb9f.mp3"}
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

    @Override
    public String toString() {
        return "MusicBean{" +
                "errcode=" + errcode +
                ", msg='" + msg + '\'' +
                ", res=" + res +
                '}';
    }

    public static class ResBean {
        /**
         * music : /files/20191120/8d84b997da43d23657676458389bfb9f.mp3
         */

        private String music;

        public String getMusic() {
            return music;
        }

        public void setMusic(String music) {
            this.music = music;
        }

        @Override
        public String toString() {
            return "ResBean{" +
                    "music='" + music + '\'' +
                    '}';
        }
    }
}
