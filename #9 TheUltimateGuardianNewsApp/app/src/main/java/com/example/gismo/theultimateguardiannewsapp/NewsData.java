package com.example.gismo.theultimateguardiannewsapp;

/**
 * Created by gismo on 7/15/2017.
 */

public class NewsData {

    private String title;
    private String sectionName;
    private String author;
    private String webPublicationDate;
    private String webUrl;

    public NewsData(String title, String sectionName, String author, String webPublicationDate, String webUrl) {
        this.title = title;
        this.sectionName = sectionName;
        this.author = author;
        this.webPublicationDate = webPublicationDate;
        this.webUrl = webUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getAuthor() {
        return author;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public String getWebUrl() {
        return webUrl;
    }
}
