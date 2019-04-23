package com.news.fanabc.Utils;

import android.content.Context;
import android.util.Log;

import com.news.fanabc.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Lang_Type {

    public String HOME;
    public String NEWS  ;
    public String BUSINESS ;
    public String SPORT   ;
    public String WORLD   ;
    public String TECH    ;
    public String VIDEO  ;
    public String HEALTH ;


    private ArrayList<UrlType> allUrl;
    private Context context;

    public ArrayList<UrlType> getAllUrl() {
        return allUrl;
    }

    public enum LangCode {
        Amharic {
            @NotNull
            public String toString() {
                return "";
            }
        }, English {
            @NotNull
            public String toString() {
                return "english";
            }
        }, AfanOromo {
            @NotNull
            public String toString() {
                return "afanoromo";
            }
        }, Tigrigna {
            @NotNull
            public String toString() {
                return "tigrigna";
            }
        }
    }

    public Lang_Type(LangCode langCode, Context context) {
        this.context = context;



        HOME = context.getString(R.string.var_home);
        NEWS = context.getString(R.string.var_news);
        BUSINESS = context.getString(R.string.var_business);
        SPORT = context.getString(R.string.var_sport);
        WORLD = context.getString(R.string.var_world);
        TECH = context.getString(R.string.var_tech);
        VIDEO = context.getString(R.string.var_video);
        HEALTH = context.getString(R.string.var_health);

        allUrl = new ArrayList<>(getLanguageUrl(langCode));

    }

    private String getLanguage(LangCode langCode) {

        return langCode.toString();
    }


    private ArrayList<UrlType> getLanguageUrl(LangCode langCode) {
        if(langCode == null)langCode = LangCode.English;
        String Language = getLanguage(langCode);
        String mainUrl = "https://fanabc.com/";

        ArrayList<UrlType> languageUrl = new ArrayList<>();
        switch (langCode) {

            case Amharic:
                languageUrl.clear();
                UrlType homeUrl = new UrlType(mainUrl + Language, HOME);
                UrlType ethiopia_news = new UrlType(mainUrl + Language + "/category/localnews/", NEWS);
                UrlType bussiness = new UrlType(mainUrl + Language + "/category/buz/", BUSINESS);
                UrlType sport = new UrlType(mainUrl + Language + "/category/%e1%88%b5%e1%8d%93%e1%88%ad%e1%89%b5/", SPORT);
                UrlType worldNews = new UrlType(mainUrl + Language + "/category/worldnews/", WORLD);
                UrlType tech = new UrlType(mainUrl + Language + "/category/tech/", TECH);
                UrlType video = new UrlType(mainUrl + Language + "/category/video/", VIDEO);
                UrlType health = new UrlType(mainUrl + Language + "/category/health/", HEALTH);

                languageUrl.add(homeUrl);
                languageUrl.add(ethiopia_news);
                languageUrl.add(bussiness);
                languageUrl.add(sport);
                languageUrl.add(worldNews);
                languageUrl.add(tech);
                languageUrl.add(video);
                languageUrl.add(health);

                break;
            case English:
                homeUrl = new UrlType(mainUrl + Language, HOME);
                ethiopia_news = new UrlType(mainUrl + Language + "/category/localnews/", NEWS);
                bussiness = new UrlType(mainUrl + Language + "/category/business/", BUSINESS);
                sport = new UrlType(mainUrl + Language + "/category/sport/", SPORT);
                worldNews = new UrlType(mainUrl + Language + "/category/worldnews/", WORLD);
                tech = new UrlType(mainUrl + Language + "/category/tech/", TECH);

                languageUrl.add(homeUrl);
                languageUrl.add(ethiopia_news);
                languageUrl.add(bussiness);
                languageUrl.add(sport);
                languageUrl.add(worldNews);
                languageUrl.add(tech);

                break;
            case AfanOromo:
                homeUrl = new UrlType(mainUrl + Language, HOME);
                ethiopia_news = new UrlType(mainUrl + Language + "/oduu/", NEWS);
                bussiness = new UrlType(mainUrl + Language + "/buz/", BUSINESS);
                sport = new UrlType(mainUrl + Language + "/blog/category/ispoortii/", SPORT);
                worldNews = new UrlType(mainUrl + Language + "/blog/category/worldnews/", WORLD);
                tech = new UrlType(mainUrl + Language + "/blog/category/teeknooloojii/", TECH);
                health = new UrlType(mainUrl + Language+"/blog/category/fayyaa/", HEALTH);

                languageUrl.add(homeUrl);
                languageUrl.add(ethiopia_news);
                languageUrl.add(bussiness);
                languageUrl.add(sport);
                languageUrl.add(worldNews);
                languageUrl.add(tech);
                languageUrl.add(health);
                break;
            case Tigrigna:
                homeUrl = new UrlType(mainUrl + Language, HOME);
                ethiopia_news = new UrlType(mainUrl + Language + "/%e1%8b%9c%e1%8a%93/", NEWS);
                bussiness = new UrlType(mainUrl + Language + "/%e1%89%a2%e1%8b%9d%e1%8a%90%e1%88%b5/", BUSINESS);
                sport = new UrlType(mainUrl + Language + "/blog/category/%e1%88%b5%e1%8d%93%e1%88%ad%e1%89%b5/", SPORT);
                worldNews = new UrlType(mainUrl + Language + "/blog/category/worldnews/", WORLD);
                tech = new UrlType(mainUrl + Language + "/blog/category/tech/", TECH);
                video = new UrlType(mainUrl + Language + "/blog/category/video/", VIDEO);
                health = new UrlType(mainUrl + Language + "/blog/category/health/", HEALTH);

                languageUrl.add(homeUrl);
                languageUrl.add(ethiopia_news);
                languageUrl.add(bussiness);
                languageUrl.add(sport);
                languageUrl.add(worldNews);
                languageUrl.add(tech);
                languageUrl.add(video);
                languageUrl.add(health);
        }

        return languageUrl;
    }
    public ArrayList<UrlType> getTabUrlType(){

        return new ArrayList<>(this.allUrl.subList(0, 4));
    }
}
