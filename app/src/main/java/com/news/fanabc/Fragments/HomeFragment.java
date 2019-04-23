/*
package com.news.fanabc.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.news.fanabc.R;
import com.prof.rssparser.Article;
import com.prof.rssparser.OnTaskCompleted;
import com.prof.rssparser.Parser;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    // private String[] strings = {"This is simple a fake title", "This is simple a fake title", "This is simple a fake title", "This is simple a fake title", "This is simple a fake title", "This is simple a fake title", "This is simple a fake title"};
    private RecyclerView recyclerView;
    private View view;
    private String feedUrl = "https://fanabc.com/feed";
    private ProgressBar progressBar;
    private TextView error_textview;
    private AppCompatButton try_again_btn;


    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_main, container, false);
        this.error_textview = this.view.findViewById(R.id.error_text_view);
        this.progressBar = this.view.findViewById(R.id.progressBar);
        this.try_again_btn = this.view.findViewById(R.id.try_again_btn);
        this.recyclerView = this.view.findViewById(R.id.recyclerview);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // If the data loaded set Adapter
        // else show error text view and try again button
        this.error_textview.setVisibility(View.GONE);
        this.try_again_btn.setVisibility(View.GONE);
        Parser rssParser = new Parser();

        rssParser.onFinish(new OnTaskCompleted() {
            @Override
            public void onTaskCompleted(@NotNull List<Article> list) {
                Log.d("uuuu", "Success Fully Loadded Article Size: "+ Integer.toString(list.size()));
                recyclerView.setAdapter(new SimpleRVAdapter(list));
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(@NotNull Exception e) {
                progressBar.setVisibility(View.GONE);
                error_textview.setVisibility(View.VISIBLE);
                try_again_btn.setVisibility(View.VISIBLE);
                Log.e("uuuu", "Error Occured " + e.getMessage());
            }
        });
        rssParser.execute(this.feedUrl);

        return this.view;
    }

    */
/**
     * A Simple Adapter for the RecyclerView
     *//*

    public class SimpleRVAdapter extends RecyclerView.Adapter<SimpleViewHolder> {
        private List<Article> dataSource;

        public SimpleRVAdapter(List<Article> dataArgs) {
            this.dataSource = dataArgs;
        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_items, parent, false);
            SimpleViewHolder simpleViewHolder = new SimpleViewHolder(view);
            return simpleViewHolder;
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, int position) {
            String titleString = dataSource.get(position).getTitle();
            String pubDateString = dataSource.get(position).getPubDate();

            String author = "Author: "+dataSource.get(position).getAuthor();


            String content = dataSource.get(position).getContent();

            Document doc = Jsoup.parse(content);
            Elements parElements = doc.getElementsByTag("p");
            content = "";
            for(Element singlePar : parElements){
                content += singlePar.text();
                content += "\r\n\r\n";
            }
            Log.d("uuuu", content);


            if (author != null) holder.author.setText(author);
            if (content != null) holder.description.setText(content);


            if(titleString != null)holder.title.setText(titleString);
            if (pubDateString != null) holder.pubDate.setText(pubDateString);
            Picasso.get()
                    .load(dataSource.get(position).getImage())
                    .placeholder(R.drawable.ic_image_blue_24dp)
                    .error(R.drawable.ic_error_red_24dp)
                    .into(holder.rss_image);

        }

        @Override
        public int getItemCount() {
            return dataSource.size();
        }
    }

    */
/**
     * A Simple ViewHolder for the RecyclerView
     *//*

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView pubDate;
        public TextView description;
        public TextView author;
        public ImageView rss_image;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            pubDate = itemView.findViewById(R.id.pubDate);
            rss_image = itemView.findViewById(R.id.rss_image);
            description = itemView.findViewById(R.id.description);
            author = itemView.findViewById(R.id.author);

        }
    }

}*/
