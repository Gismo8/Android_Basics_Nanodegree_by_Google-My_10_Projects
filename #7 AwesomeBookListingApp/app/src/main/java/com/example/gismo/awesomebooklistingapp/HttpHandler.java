package com.example.gismo.awesomebooklistingapp;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by gismo on 7/13/2017.
 */

public class HttpHandler {

    private static final String LOG_TAG = HttpHandler.class.getSimpleName();
    private static final String BASE_URL_STRING = "https://www.googleapis.com/books/v1/volumes";

    private HttpHandler() {
    }

    static String fetchBookData(String query) {
        String response = null;

        try {
            URL url = makeUrl(query);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            if (httpURLConnection.getResponseCode() == 200) {
                InputStream inputStream =
                        new BufferedInputStream(httpURLConnection.getInputStream());
                response = convertStreamToString(inputStream);
            } else {
                Log.e(LOG_TAG, "Request error. Status code: " + httpURLConnection.getResponseCode() +
                        " " + httpURLConnection.getResponseMessage());
            }
        } catch (ProtocolException e) {
            Log.e(LOG_TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(LOG_TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception: " + e.getMessage());
        }

        return response;
    }

    private static URL makeUrl(String query) {
        URL builtUrl = null;
        try {
            query = URLEncoder.encode(query, "UTF-8");
            URL baseUrl = new URL(BASE_URL_STRING);
            builtUrl = new URL(baseUrl, "?maxResults=40&q=" + query);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "MalformedURLException: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            Log.e(LOG_TAG, "UnsupportedEncodingException: " + e.getMessage());
        }
        return builtUrl;
    }

    private static String convertStreamToString(InputStream inputStream) {
        if (inputStream != null) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            try {
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "BufferedReader error: " + e.getMessage());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "inputStream closing error: " + e.getMessage());
                }
            }
            return stringBuilder.toString();
        } else {
            return "";
        }
    }
}
