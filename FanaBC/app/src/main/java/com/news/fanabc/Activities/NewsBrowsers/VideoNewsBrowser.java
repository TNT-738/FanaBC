package com.news.fanabc.Activities.NewsBrowsers;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import com.news.fanabc.R;
import com.news.fanabc.Utils.Article;
import com.news.fanabc.Utils.NetworkManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nibor.autolink.LinkExtractor;
import org.nibor.autolink.LinkSpan;
import org.nibor.autolink.LinkType;
import org.nibor.autolink.Span;

import java.util.EnumSet;

public class VideoNewsBrowser extends AppCompatActivity {

    private Article selectedArticle;
    private WebView youtube_player;
    private String youtubeLink;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_news_browser);
        youtube_player = findViewById(R.id.youtube_player);
        youtube_player.getSettings().setJavaScriptEnabled(true);
       selectedArticle = (Article) getIntent().getSerializableExtra("article_key");
        NetworkManager networkManager = new NetworkManager(this);
        networkManager.setResponseListener(new NetworkManager.ResponseListener() {
            @Override
            public void onSuccessfullResponse(String Response) {

                Document document = Jsoup.parse(Response);
                Elements elements = document.getElementsByTag("article");
                for(Element element : elements){
                    for(Element iframeElement : element.getElementsByTag("iframe")){
                        youtubeLink = iframeElement.attr("src");
                    }
                }
               // Checking youtube link searching on the article

                if(youtubeLink == null){
                    Elements youTubeContainingElements = document.getElementsContainingText("youtube");
                    if(!youTubeContainingElements.isEmpty()){
                        String youtubeLinkArticle = youTubeContainingElements.get(0).text();
                        int index_of_youtube = youtubeLinkArticle.indexOf("youtube.com/");
                        if(index_of_youtube >= 0) {
                            String s = "";
                            for (int c = index_of_youtube; c < youtubeLinkArticle.length(); c++){
                                    if(youtubeLinkArticle.charAt(c) == ' ')break;
                                    s += youtubeLinkArticle.charAt(c);
                            }
                            youtubeLink =  "https://www." + s;
                        }
                        Log.d("cccc", youtubeLink);
                    }

                }
                if(youtubeLink != null) {
                    int embed_slash_index = youtubeLink.indexOf("embed/");
                    if(embed_slash_index > 0){
                        embed_slash_index = embed_slash_index + "embed/".length();
                        int question_mark_index = -1;
                        for(int i = embed_slash_index ; i < youtubeLink.length(); i++){
                            if(youtubeLink.charAt(i) == '?'){
                                question_mark_index = i;
                                break;
                            }
                        }
                        youtubeLink = "https://www.youtube.com/watch?v=" + youtubeLink.substring(embed_slash_index, question_mark_index);
                    }
                    youtube_player.loadUrl(youtubeLink);

                }
                else {
                    Toast.makeText(context, "Unable to load the video!", Toast.LENGTH_LONG).show();
                }
                
            }

            @Override
            public void onErrorResponse(String ErrorMessage) {

            }
        });
        networkManager.HttpGetRequest(selectedArticle.getNewsLink());
    }
}
