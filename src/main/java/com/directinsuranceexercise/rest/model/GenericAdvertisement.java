package com.directinsuranceexercise.rest.model;

import com.directinsuranceexercise.rest.utilities.AdvertisementUtils;
import com.directinsuranceexercise.rest.utilities.Constants;

import java.util.logging.Logger;

/**
 * The GenericAdvertisement class is a prototype for generating general advertisement with basic properties
 * such as ID, category,contact name, contact phone number and price.
 *
 * Mainly used for inheritance by specific ad categories ( car, asset etc ).
 *
 * @author shirirave
 * @since 18/04/2023
 */
public class GenericAdvertisement {
    private static final Logger logger = Logger.getLogger(GenericAdvertisement.class.getName());

    private String id;

    // a placeholder to be overriden by future categories
    private String category = Constants.genericCategory;
    private String contactName;
    private String contactPhoneNumber;
    private Double price;


    /**
     * Creates a new instance of the GenericAdvertisement class with default values for its properties.
     */
    public GenericAdvertisement() {
    }

    /**
     * Creates a new instance of the GenericAdvertisement class with the given values for its properties.
     *
     * @param price The price of the advertisement.
     * @param contactName The name of the contact person for the advertisement.
     * @param contactPhoneNumber The phone number of the contact person for the advertisement.
     */

    public GenericAdvertisement(Double price, String contactName, String contactPhoneNumber) {
        this.id = AdvertisementUtils.createId();
        this.contactName = contactName;
        this.contactPhoneNumber = contactPhoneNumber;
        this.price = price;
    }

    /**
     * Returns the ID of the advertisement.
     *
     * @return The ID of the advertisement.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the advertisement.
     *
     * @param id The ID of the advertisement.
     */
    public void setId(String id) {
        this.id = id;
        logger.info("setId() called with ID: " + id);
    }

    /**
     * Returns the category of the advertisement.
     *
     * @return The category of the advertisement.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the advertisement.
     *
     * @param category The category of the advertisement.
     */
    public void setCategory(String category) {
        this.category = category;
        logger.info("setCategory() called with category: " + category);
    }

    /**
     * Returns the name of the contact person for the advertisement.
     *
     * @return The name of the contact person for the advertisement.
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the name of the contact person for the advertisement.
     *
     * @param contactName The name of the contact person for the advertisement.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
        logger.info("setContactName() called with name: " + contactName);
    }

    /**
     * Returns the phone number of the contact person for the advertisement.
     *
     * @return The phone number of the contact person for the advertisement.
     */
    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    /**
     * Sets the phone number of the contact person for the advertisement.
     *
     * @param contactPhoneNumber The phone number of the contact person for the advertisement.
     */
    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
        logger.info("setContactPhoneNumber() called with phone number: " + contactPhoneNumber);
    }

    /**
     * Sets the price of the advertisement.
     *
     * @param price the price of the advertisement
     */
    public void setPrice(Double price) {
        this.price = price;
        logger.info("setPrice() called with price: " + price);
    }

    /**
     * Returns the price of the advertisement.
     *
     * @return the price of the advertisement
     */
    public Double getPrice() {
        return price;
    }


}
