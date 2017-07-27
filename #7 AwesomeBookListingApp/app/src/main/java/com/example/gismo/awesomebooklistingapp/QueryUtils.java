package com.example.gismo.awesomebooklistingapp;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gismo on 7/13/2017.
 */

public class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils() {
    }

    static List<Book> extractBooks(String jsonString) {
        List<Book> bookList = new ArrayList<>();

        try {
            JSONObject rootJson = new JSONObject(jsonString);
            JSONArray bookItems = rootJson.getJSONArray("items");
            for (int i = 0; i < bookItems.length(); i++) {
                JSONObject book = bookItems.getJSONObject(i);
                JSONObject bookInfo = book.getJSONObject("volumeInfo");
                JSONObject bookImageInfo =bookInfo.getJSONObject("imageLinks");

                String title = bookInfo.getString("title");

                List<String> authorList = new ArrayList<>();

                if (bookInfo.has("authors")) {
                    JSONArray authors = bookInfo.getJSONArray("authors");
                    for (int j = 0; j < authors.length(); j++) {
                        authorList.add(authors.getString(j));
                    }
                }

                String imageUrl = bookImageInfo.getString("smallThumbnail");

                String infoLink = bookInfo.getString("infoLink");
                bookList.add(new Book(title, authorList, infoLink, imageUrl));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "JSON parsing error: " + e.getMessage());
        }
        return bookList;
    }
}
