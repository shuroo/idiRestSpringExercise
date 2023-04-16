package com.directinsuranceexercise.rest.model;

import com.directinsuranceexercise.rest.utilities.Constants;

public class CarAdvertisement extends GenericAdvertisement {

    protected String manifacturer;
    protected String model;
    protected int year;
    protected int km;
    protected String color;

    protected String category = Constants.carCategory;
    public String getManufacturer() {
        return manifacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manifacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
