package com.example.gismo.debrecentourguide;

/**
 * Created by gismo on 6/18/2017.
 */

public class HotelVM implements ListItem {

    protected String name;
    protected String details;
    protected String stars;
    protected String priceRange;

    public HotelVM(String name, String details, String stars, String priceRange) {
        this.name = name;
        this.details = details;
        this.stars = stars;
        this.priceRange = priceRange;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDetails() {
        return details;
    }

    @Override
    public String getPriceRange() {
        return priceRange;
    }

    @Override
    public String getOpeningTime() {
    return null;
    }

    @Override
    public int getPhoto() {
        return 0;
    }

    @Override
    public String getStarCount() {
        return stars;
    }
}
