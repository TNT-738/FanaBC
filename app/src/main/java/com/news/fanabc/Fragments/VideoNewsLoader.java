package com.news.fanabc.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.news.fanabc.Activities.NewsBrowsers.TextNewsBrowser;
import com.news.fanabc.Activities.NewsBrowsers.VideoNewsBrowser;
import com.news.fanabc.R;
import com.news.fanabc.Utils.Article;
import com.news.fanabc.Utils.Lang_Type;
import com.news.fanabc.Utils.NetworkManager;
import com.news.fanabc.Utils.SettingManager;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.view.View.GONE;

public class VideoNewsLoader extends Fragment {
    private RecyclerView recyclerView;
    private String newsUrl = "https://fanabc.com/category/video/";
    private ProgressBar progressBar;
    private TextView error_textview;
    private AppCompatButton try_again_btn;



    private SimpleRVAdapter simpleRVAdapter;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


            Lang_Type.LangCode settingLanguage = SettingManager.SettingBuilder(getActivity()).getSettingLanguage();
            if(settingLanguage.equals(Lang_Type.LangCode.Amharic) || settingLanguage.equals(Lang_Type.LangCode.Tigrigna)){
                this.newsUrl = new Lang_Type(settingLanguage, getActivity()).getAllUrl().get(6).getUrl();
            }

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        this.error_textview = view.findViewById(R.id.error_text_view);

        this.progressBar = view.findViewById(R.id.progressBar);

        this.try_again_btn = view.findViewById(R.id.try_again_btn);
        this.try_again_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadNews();
            }
        });


        this.recyclerView = view.findViewById(R.id.recyclerview);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LoadNews();


        // If the data loaded set Adapter
        // else show error text view and try again button
        return view;
    }

    /**
     * A Simple Adapter for the RecyclerView
     */
    private void LoadNews() {
        progressBar.setVisibility(View.VISIBLE);
        this.error_textview.setVisibility(GONE);
        this.try_again_btn.setVisibility(GONE);
        NetworkManager networkManager = new NetworkManager(getContext());

        networkManager.setResponseListener(new NetworkManager.ResponseListener() {
            @Override
            public void onSuccessfullResponse(String Response) {
                progressBar.setVisibility(GONE);
                Document document = Jsoup.parse(Response);
                Elements articleElements = document.getElementsByTag("article");
                ArrayList<Article> articleArrayList = new ArrayList<>();
                for (Element articleElement : articleElements) {
                    Article article = new Article();
                    article.setArticleType(article.identifyArticleType(articleElement.outerHtml()));
                    Elements elements_with_data_src;
                    elements_with_data_src = articleElement.getElementsByAttribute("data-src");
                    if (!elements_with_data_src.isEmpty()) {
                        for (Element element : elements_with_data_src) {
                            String title = element.attr("title");
                            String imageLink = element.attr("data-src");
                            String newsLink = element.attr("href");
                            article.setTitle(title);
                            article.setImageLink(imageLink);
                            article.setNewsLink(newsLink);
                        }
                    }
                    Elements articlepubdates = articleElement.getElementsByTag("time");
                    if (!articlepubdates.isEmpty())
                        article.setPublisheddate(articlepubdates.get(0).text());


                    Elements articleAuthors = articleElement.getElementsByClass("post-author author");
                    if (!articleAuthors.isEmpty()) article.setAuthor(articleAuthors.get(0).text());


                    articleArrayList.add(article);
                }
                simpleRVAdapter = new SimpleRVAdapter(articleArrayList);
                recyclerView.setAdapter(simpleRVAdapter);
                simpleRVAdapter.notifyDataSetChanged();

            }

            @Override
            public void onErrorResponse(String ErrorMessage) {
                error_textview.setVisibility(View.VISIBLE);
                try_again_btn.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
        networkManager.HttpGetRequest(this.newsUrl);

    }



    public class SimpleRVAdapter extends RecyclerView.Adapter<SimpleViewHolder> {
        private ArrayList<Article> dataSource;

        SimpleRVAdapter(ArrayList<Article> dataArgs) {
            this.dataSource = dataArgs;
        }

        @NotNull
        @Override
        public SimpleViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.other_video_list_items, parent, false);
            return new SimpleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NotNull SimpleViewHolder holder, final int position) {
            String titleString = dataSource.get(position).getTitle();
            String pubDate = dataSource.get(position).getPublisheddate();
            String author = dataSource.get(position).getAuthor();

            if (titleString != null) holder.title.setText(titleString);
            if (pubDate != null) holder.pubdate.setText(String.format("Published: %s", pubDate));
            if (author != null) holder.author.setText(String.format("Author: %s", author));

            if (dataSource.get(position).getArticleType() != null)
                setTag(dataSource.get(position).getArticleType(), holder);
            Picasso.get()
                    .load(dataSource.get(position).getImageLink())
                    .placeholder(R.drawable.ic_image_blue_24dp)
                    .error(R.drawable.ic_error_red_24dp)
                    .into(holder.rss_image);
            holder.rss_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (dataSource.get(position).getArticleType()){
                        case TEXT:
                            Intent newActivityStarter = new Intent(getActivity(), TextNewsBrowser.class);
                            newActivityStarter.putExtra("article_key", dataSource.get(position));
                            startActivity(newActivityStarter);
                            break;
                        case VIDEO:
                            Intent newVideoBrowser = new Intent(getActivity(), VideoNewsBrowser.class);
                            newVideoBrowser.putExtra("article_key", dataSource.get(position));
                            startActivity(newVideoBrowser);
                            break;
                    }
                    getActivity().finish();
                }
            });

        }

        @Override
        public int getItemCount() {
            return dataSource.size();
        }
    }

    /**
     * A Simple ViewHolder for the RecyclerView
     */
    class SimpleViewHolder extends RecyclerView.ViewHolder {
        TextView title, pubdate, author;
        ImageView rss_image;
        LinearLayout tagLayout;
        TextView tagText;

        SimpleViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            pubdate = itemView.findViewById(R.id.pubDate);
            author = itemView.findViewById(R.id.author);
            rss_image = itemView.findViewById(R.id.rss_image);
            tagLayout = itemView.findViewById(R.id.tag_layout);
            tagText = itemView.findViewById(R.id.tag_text);
        }
    }
    private void setTag(Article.ArticleType articleType, VideoNewsLoader.SimpleViewHolder simpleViewHolder) {
        switch (articleType) {
            case VIDEO:
                if (simpleViewHolder.tagLayout.getVisibility() != View.VISIBLE)
                    simpleViewHolder.tagLayout.setVisibility(View.VISIBLE);
                simpleViewHolder.tagText.setText(getString(R.string.video_text));
                break;
            case AUDIO:
                if (simpleViewHolder.tagLayout.getVisibility() != View.VISIBLE)
                    simpleViewHolder.tagLayout.setVisibility(View.VISIBLE);
                simpleViewHolder.tagText.setText(getString(R.string.audio_text));
                break;
            default:
                if (simpleViewHolder.tagLayout.getVisibility() != View.GONE) simpleViewHolder.tagLayout.setVisibility(GONE);

        }
    }

}