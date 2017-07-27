package com.example.gismo.awesomebooklistingapp;

import java.util.List;

/**
 * Created by gismo on 7/13/2017.
 */

public class Book {

    private String title;
    private List<String> authors;
    private String infoLink;
    private String imageUrl;

    public Book(String title, List<String> authors, String infoLink, String imageUrl) {
        this.title = title;
        this.authors = authors;
        this.infoLink = infoLink;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
