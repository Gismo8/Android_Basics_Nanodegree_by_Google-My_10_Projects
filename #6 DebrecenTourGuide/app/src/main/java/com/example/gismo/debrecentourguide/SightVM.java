package com.example.gismo.debrecentourguide;

/**
 * Created by gismo on 6/18/2017.
 */

public class SightVM implements ListItem{

    protected String name;
    protected String details;
    protected int drawableSrc;

    public SightVM(String name, String details, int drawableSrc) {
        this.name = name;
        this.details = details;
        this.drawableSrc = drawableSrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String getPriceRange() {
        return null;
    }

    @Override
    public String getOpeningTime() {
        return null;
    }

    @Override
    public int getPhoto() {
        return drawableSrc;
    }

    @Override
    public String getStarCount() {
        return null;
    }

}
