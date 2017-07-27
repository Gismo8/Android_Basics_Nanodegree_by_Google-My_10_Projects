package com.example.gismo.awesomebooklistingapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by gismo on 7/13/2017.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    private String query;

    BookLoader(Context context, String query) {
        super(context);
        this.query = query;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        if (query == null) {
            return null;
        }

        String jsonResponse = HttpHandler.fetchBookData(query);
        List<Book> bookList = QueryUtils.extractBooks(jsonResponse);

        return bookList;
    }
}
