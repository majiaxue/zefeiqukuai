package com.example.zefeiqukuai.bean;

import java.io.Serializable;

public class NewVersionBean implements Serializable
{

    /**
     * version : 1.0.1
     * url : https://fir.im/1l9f
     * content : 拦着我  放大招啦
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
}
