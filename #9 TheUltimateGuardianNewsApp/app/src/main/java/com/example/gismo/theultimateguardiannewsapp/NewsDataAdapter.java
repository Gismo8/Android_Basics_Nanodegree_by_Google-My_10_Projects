package com.example.gismo.theultimateguardiannewsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gismo on 7/15/2017.
 */

public class NewsDataAdapter extends RecyclerView.Adapter<NewsDataAdapter.ViewHolder> {

    private OnItemClickListener listener;
    private List<NewsData> newsDatas;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sectionTextView;
        TextView titleTextView;
        TextView authorTextView;
        TextView publishedTextView;

        public ViewHolder(final View itemView) {
            super(itemView);

            sectionTextView = (TextView) itemView.findViewById(R.id.section);
            titleTextView = (TextView) itemView.findViewById(R.id.title);
            authorTextView = (TextView) itemView.findViewById(R.id.author);
            publishedTextView = (TextView) itemView.findViewById(R.id.published);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public NewsDataAdapter(Context context, List<NewsData> news) {
        newsDatas = news;
        this.context = context;
    }

    @Override
    public NewsDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View newsListItemView = inflater.inflate(R.layout.news_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(newsListItemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsDataAdapter.ViewHolder viewHolder, int position) {
        NewsData currentNews = newsDatas.get(position);

        String convertedDateTime ="";
        String originalDateTime = currentNews.getWebPublicationDate();
        String substringDateTime = originalDateTime.substring(0, 16);
        String convertedDateTime1 = substringDateTime.replace("-", ".");
        convertedDateTime = convertedDateTime1.replace("T", ", ");

        viewHolder.sectionTextView.setText(currentNews.getSectionName());
        viewHolder.titleTextView.setText(currentNews.getTitle());
        viewHolder.authorTextView.setText(currentNews.getAuthor());
        viewHolder.publishedTextView.setText(convertedDateTime);

    }

    @Override
    public int getItemCount() {
        return newsDatas.size();
    }
    public void setNewsInfoList(List<NewsData> newsList) {
        newsDatas = newsList;
    }
}
