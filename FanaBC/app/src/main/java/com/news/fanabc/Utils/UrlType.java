package com.news.fanabc.Utils;

public class UrlType {

    public static String HOME = "HOME";
    public static String NEWS = "NEWS";
    public static String BUSINESS = "BUSINESS";
    public static String SPORT = "SPORT";
    public static String WORLD = "WORLD";
    public static String TECH = "TECH";
    public static String HEALTH = "HEALTH";
    public static String FANA_COLLECTION = "FANA COLLECTION";


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