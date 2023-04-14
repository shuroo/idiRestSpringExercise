package com.directinsuranceexercise.rest.model;

import com.directinsuranceexercise.rest.utilities.Constants;

import java.util.logging.Logger;

public class GenericAdvertisement {
    private static final Logger logger = Logger.getLogger(GenericAdvertisement.class.getName());

    private String id;

    // a placeholder to be overriden by future categories
    private String category = Constants.genericCategory;
    private String contactName;
    private String contactPhoneNumber;
    private Double price;

    public GenericAdvertisement(){}
    public GenericAdvertisement(String id, String category, String contactName, String contactPhoneNumber, Double price) {
        this.id = id;
        this.category = category;
        this.contactName = contactName;
        this.contactPhoneNumber = contactPhoneNumber;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        logger.info("setId() called with ID: " + id);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        logger.info("setCategory() called with category: " + category);
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
        logger.info("setContactName() called with name: " + contactName);
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
        logger.info("setContactPhoneNumber() called with phone number: " + contactPhoneNumber);
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
        logger.info("setPrice() called with price: " + price);
    }

    public void logAdvertisement() {
        logger.info("Ad ID: " + id + ", Category: " + category + ", Contact Name: " + contactName + ", Contact Phone Number: " + contactPhoneNumber + ", Price: " + price);
    }
}
