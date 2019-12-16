package com.example.zefeiqukuai.bean;

public class CheckUpBean {

    /**
     * version : 1.0.2
     * url : https://fir.im/1l9f
     * content : 我要更新啦  不要拦着我
     */

    private String version;
    private String url;
    private String content;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CheckUpBean{" +
                "version='" + version + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
