package com.news.fanabc.Utils;

import java.util.ArrayList;

import static com.news.fanabc.Utils.UrlType.BUSINESS;
import static com.news.fanabc.Utils.UrlType.FANA_COLLECTION;
import static com.news.fanabc.Utils.UrlType.HEALTH;
import static com.news.fanabc.Utils.UrlType.NEWS;
import static com.news.fanabc.Utils.UrlType.SPORT;
import static com.news.fanabc.Utils.UrlType.TECH;
import static com.news.fanabc.Utils.UrlType.WORLD;

public class Lang_Type {
    public enum LangCode {
        Amharic {
            public String toString() {
                return "";
            }
        }, English {
            public String toString() {
                return "english";
            }
        }, AfanOromo {
            public String toString() {
                return "afanoromo";
            }
        }, Tigrigna {
            public String toString() {
                return "tigrigna";
            }
        }
    }

    private String getLanguage(LangCode langCode) {

        return langCode.toString();
    }


    public ArrayList<UrlType> getLanguageUrl(LangCode langCode) {
        String Language = getLanguage(langCode);
        String mainUrl = "https://fanabc.com/";

        ArrayList<UrlType> languageUrl = new ArrayList<>();
        switch (langCode) {

            case Amharic:
                languageUrl.clear();
                UrlType homeUrl = new UrlType(mainUrl + Language, UrlType.HOME);
                UrlType ethiopia_news = new UrlType(mainUrl + Language + "/category/localnews/", NEWS);
                UrlType bussiness = new UrlType(mainUrl + Language + "/category/buz/", BUSINESS);
                UrlType sport = new UrlType(mainUrl + Language + "/category/%e1%88%b5%e1%8d%93%e1%88%ad%e1%89%b5/", SPORT);
                UrlType worldNews = new UrlType(mainUrl + Language + "/category/worldnews/", WORLD);
                UrlType tech = new UrlType(mainUrl + Language + "/category/tech/", TECH);
                UrlType health = new UrlType(mainUrl + Language + "/category/health/", HEALTH);
                UrlType fana_collection = new UrlType(mainUrl + Language + "/category/%e1%88%b5%e1%89%a5%e1%88%b5%e1%89%a5/", FANA_COLLECTION);

                languageUrl.add(homeUrl);
                languageUrl.add(ethiopia_news);
                languageUrl.add(bussiness);
                languageUrl.add(sport);
                languageUrl.add(worldNews);
                languageUrl.add(tech);
                languageUrl.add(health);
                languageUrl.add(fana_collection);
                break;
        }

        return languageUrl;
    }
}
