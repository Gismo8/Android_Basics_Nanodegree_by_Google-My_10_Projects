package com.example.gismo.theultimateguardiannewsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by gismo on 7/15/2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<NewsData>> {

    private static final String LOG_TAG = NewsLoader.class.getName();
    private String url;


    public NewsLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsData> loadInBackground() {
        if (url == null) {
            return null;
        }

        List<NewsData> newsList = QueryUtils.fetchNewsData(url);
        return newsList;
    }
}