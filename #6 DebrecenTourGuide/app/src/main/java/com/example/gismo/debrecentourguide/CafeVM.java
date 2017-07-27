package com.example.gismo.debrecentourguide;

/**
 * Created by gismo on 6/18/2017.
 */

public class CafeVM implements ListItem {

    protected String name;
    protected String details;
    protected String openingTime;
    protected String priceRange;

    public CafeVM(String name, String details, String openingTime, String priceRange) {
        this.name = name;
        this.details = details;
        this.openingTime = openingTime;
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
        return openingTime;
    }

    @Override
    public int getPhoto() {
        return 0;
    }

    @Override
    public String getStarCount() {
        return null;
    }
}
