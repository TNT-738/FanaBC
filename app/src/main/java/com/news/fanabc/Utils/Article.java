package com.news.fanabc.Utils;

import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable {



    public enum ArticleType {
        VIDEO, AUDIO, TEXT
    }


    private String title;
    private String newsLink;
    private String imageLink;
    private String publisheddate;
    private String author;
    private ArticleType articleType;


    protected Article(Parcel in) {
        title = in.readString();
        newsLink = in.readString();
        imageLink = in.readString();
        publisheddate = in.readString();
        author = in.readString();
        this.articleType = ArticleType.values()[in.readInt()];
    }
    public Article(){

    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(newsLink);
        dest.writeString(imageLink);
        dest.writeString(publisheddate);
        dest.writeString(author);
        dest.writeInt(this.articleType.ordinal());
    }


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
