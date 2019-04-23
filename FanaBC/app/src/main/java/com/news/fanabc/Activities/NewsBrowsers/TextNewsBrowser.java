package com.news.fanabc.Activities.NewsBrowsers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.news.fanabc.R;
import com.news.fanabc.Utils.Article;
import com.news.fanabc.Utils.NetworkManager;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TextNewsBrowser extends AppCompatActivity {

    private TextView title, pubDate, author, content;
    private ImageView newsPhoto;
    private Article selectedArticle;

    private String TAG = TextNewsBrowser.class.getSimpleName();
    private ProgressBar progressbar;
    private TextView error_text_view;
    private AppCompatButton try_again_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = findViewById(R.id.text_news_title);
        pubDate = findViewById(R.id.text_news_pub_date);
        author = findViewById(R.id.text_news_author);
        content = findViewById(R.id.text_news_content);
        newsPhoto = findViewById(R.id.text_news_image);

        progressbar = findViewById(R.id.progressBar);
        error_text_view = findViewById(R.id.error_text_view);
        try_again_btn = findViewById(R.id.try_again_btn);

        error_text_view.setVisibility(View.GONE);
        try_again_btn.setVisibility(View.GONE);

        progressbar.setVisibility(View.VISIBLE);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.hide();
        selectedArticle = (Article) getIntent().getSerializableExtra("article_key");
        Log.d("rrrr", selectedArticle.getNewsLink());
        NetworkManager networkManager = new NetworkManager(this);
        networkManager.setResponseListener(new NetworkManager.ResponseListener() {
            @Override
            public void onSuccessfullResponse(String Response) {

                Document document = Jsoup.parse(Response);
                Elements articleElements = document.getElementsByTag("article");

                for(Element element : articleElements){
                    Log.d("llll", element.outerHtml());
                    String photoUrl = null;
                    Elements contentPosts = element.getElementsByClass("entry-content clearfix single-post-content");
                    String articleContent = "";
                    for(Element cp : contentPosts) {
                       Elements contentVal = cp.getAllElements();

                        for(Element singleContent : contentVal){
                            if(singleContent.children().isEmpty())
                            articleContent = articleContent + singleContent.text() +"\r\n\r\n";
                        }

                    }
                        Elements photoElemenets = element.getElementsByTag("img");
                        for (Element photoElement : photoElemenets) {
                            photoUrl = photoElement.attr("data-src");
                            Log.d("rrrr", photoUrl);
                            break;
                        }

                    //Log.d("rrrr", articleContent);
                    //Log.d("rrrr", "Photo Url: " + element.outerHtml());
                    content.setText(articleContent);
                    title.setText(selectedArticle.getTitle());
                    if(selectedArticle.getPublisheddate() != null)pubDate.setText(String.format("Published At: %s", selectedArticle.getPublisheddate()));
                    if(selectedArticle.getAuthor() != null)author.setText(String.format("Author: %s", selectedArticle.getAuthor()));
                    Picasso.get()
                            .load(photoUrl)
                            .placeholder(R.drawable.ic_image_blue_24dp)
                            .error(R.drawable.ic_error_red_24dp)
                            .into(newsPhoto, new Callback() {
                                @Override
                                public void onSuccess() {
                                    progressbar.setVisibility(View.GONE);
                                    fab.show();
                                }

                                @Override
                                public void onError(Exception e) {
                                    error_text_view.setVisibility(View.VISIBLE);
                                    try_again_btn.setVisibility(View.VISIBLE);
                                }
                            });
                    break;
                }
            }

            @Override
            public void onErrorResponse(String ErrorMessage) {

            }
        });
        networkManager.HttpGetRequest(selectedArticle.getNewsLink());

    }
}
