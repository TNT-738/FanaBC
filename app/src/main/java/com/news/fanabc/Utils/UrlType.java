package com.news.fanabc.Utils;

import android.content.Context;

public class UrlType {



    String url;
    String Type;

    public UrlType(String url, String type) {
        this.url = url;
        this.Type = type;

    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return Type;
    }
}