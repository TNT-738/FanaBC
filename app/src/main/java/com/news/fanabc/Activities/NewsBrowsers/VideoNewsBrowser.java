package com.news.fanabc.Activities.NewsBrowsers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.news.fanabc.R;
import com.news.fanabc.Utils.Article;
import com.news.fanabc.Utils.NetworkManager;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class VideoNewsBrowser extends AppCompatActivity {

    //String videoUrl = "https://fanabc.com/category/video/";
    private Article selectedArticle;
    private YouTubePlayerView youtube_player;
    private String youtubeLink;
    //private Context context = this;

    ProgressBar progressBar;
    TextView error_textview;
    Button try_again_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_news_browser);

        progressBar = findViewById(R.id.progressBar);
        error_textview = findViewById(R.id.error_text_view);
        try_again_btn = findViewById(R.id.try_again_btn);

        error_textview.setVisibility(View.GONE);
        try_again_btn.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        selectedArticle = (Article) getIntent().getParcelableExtra("article_key");
        NetworkManager networkManager = new NetworkManager(this);
        networkManager.setResponseListener(new NetworkManager.ResponseListener() {
            @Override
            public void onSuccessfullResponse(String Response) {

                Document document = Jsoup.parse(Response);
                Elements elements = document.getElementsByTag("article");
                for (Element element : elements) {
                    for (Element iframeElement : element.getElementsByTag("iframe")) {
                        youtubeLink = iframeElement.attr("src");
                    }
                }
                // Checking youtube link searching on the article

                if (youtubeLink == null) {
                    Elements youTubeContainingElements = document.getElementsContainingText("youtube");
                    if (!youTubeContainingElements.isEmpty()) {
                        String youtubeLinkArticle = youTubeContainingElements.get(0).text();
                        int index_of_youtube = youtubeLinkArticle.indexOf("youtube.com/");
                        if (index_of_youtube >= 0) {
                            String s = "";
                            for (int c = index_of_youtube; c < youtubeLinkArticle.length(); c++) {
                                if (youtubeLinkArticle.charAt(c) == ' ') break;
                                s += youtubeLinkArticle.charAt(c);
                            }
                            youtubeLink = "https://www." + s;
                        }
                        //Log.d("cccc", youtubeLink);
                    }

                }
                if (youtubeLink != null) {
                    int embed_slash_index = youtubeLink.indexOf("embed/");
                    if (embed_slash_index > 0) {
                        embed_slash_index = embed_slash_index + "embed/".length();
                        int question_mark_index = -1;
                        for (int i = embed_slash_index; i < youtubeLink.length(); i++) {
                            if (youtubeLink.charAt(i) == '?') {
                                question_mark_index = i;
                                break;
                            }
                        }
                        String videoId = youtubeLink.substring(embed_slash_index, question_mark_index);
                        youtubeLink = "https://www.youtube.com/watch?v=" + youtubeLink.substring(embed_slash_index, question_mark_index);
                        playVideo(videoId);
                    }
                    progressBar.setVisibility(View.GONE);
                } else {

                }
            }

            @Override
            public void onErrorResponse(String ErrorMessage) {

            }
        });
        networkManager.HttpGetRequest(selectedArticle.getNewsLink());
        youtube_player = findViewById(R.id.youtube_player);
        //youtube_player.setVisibility(View.GONE);
        getLifecycle().addObserver(youtube_player);
    }

    void playVideo(final String finalVideoId){
        Log.d("cccc", finalVideoId);
        youtube_player.setVisibility(View.VISIBLE);
        youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(finalVideoId, 0);
            }
        });
    }

}
