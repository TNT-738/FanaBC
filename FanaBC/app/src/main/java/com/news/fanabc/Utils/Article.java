package com.news.fanabc.Utils;

import java.io.Serializable;

public class Article implements Serializable {

    public enum ArticleType {
        VIDEO, AUDIO, TEXT
    }


    private String title;
    private String newsLink;
    private String imageLink;
    private String publisheddate;
    private String author;
    private ArticleType articleType;

    public ArticleType getArticleType() {
        return articleType;
    }

    public void setArticleType(ArticleType articleType) {
        this.articleType = articleType;
    }




    public ArticleType identifyArticleType(String articleText) {
        articleText = articleText.toLowerCase();
        if (articleText.contains("video")) {
            return ArticleType.VIDEO;
        } else if (articleText.contains("audio")) {
            return ArticleType.AUDIO;
        } else {
            return ArticleType.TEXT;
        }
    }

    public String getPublisheddate() {
        return publisheddate;
    }

    public void setPublisheddate(String publisheddate) {
        this.publisheddate = publisheddate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsLink() {
        return newsLink;
    }

    public void setNewsLink(String newsLink) {
        this.newsLink = newsLink;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
