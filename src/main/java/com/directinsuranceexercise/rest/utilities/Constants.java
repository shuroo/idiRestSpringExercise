package com.directinsuranceexercise.rest.utilities;

/**
 * This class holds constant values used throughout the project.
 *
 * @author shirirave
 * @since 18/04/2023
 *
 */

public class Constants {
    /**
     * The base URL used in the project
     *  ** Comment: I believe this param better be defined in the config file ***
     */
    public static final String baseUrl = "http://localhost:8080/";

    /**
     * An empty string constant used in the project.
     */
    public static final String emptyString = "";

// Category Constants:
    /**
     * A constant representing all advertisement categories.
     */
    public static final String genericCategory = "All";

    /**
     * A constant representing the asset category.
     */
    public static final String assetCategory = "asset";

    /**
     * A constant representing the car category.
     */
    public static final String carCategory = "car";

    /**
     * A constant representing the electricity category.
     */
    public static final String electricityCategory = "electricity";


// ***************** Advertisement Param names as constants:  *****************

// General Advertisements constants:

    /**
     * A constant representing the id property in the advertisement.
     */
    public final static String id = "id";

    /**
     * A constant representing the price property in the advertisement.
     */
    public final static String price = "price";

    /**
     * A constant representing the category property in the advertisement.
     */
    public final static String category = "category";

    /**
     * A constant representing the contact name property in the advertisement.
     */
    public final static String contactName = "contactName";

    /**
     * A constant representing the contact phone number property in the advertisement.
     */
    public final static String contactPhoneNumber = "contactPhoneNumber";

// Asset Constants:
    /**
     * A constant representing the number of rooms property in the asset advertisement.
     */
    public final static String numberOfRooms = "numberOfRooms";

    /**
     * A constant representing the asset ad type property in the asset advertisement.
     */
    public final static String assetAdType = "assetAdType";

    /**
     * A constant representing the asset size property in the asset advertisement.
     */
    public final static String assetSize = "assetSize";

// Car Advertisements Constants:

    /**
     * A constant representing the manufacturer property in the car advertisement.
     */
    public final static String manufacturer = "manufacturer";

    /**
     * A constant representing the color property in the car advertisement.
     */
    public final static String color = "color";

    /**
     * A constant representing the model property in the car advertisement.
     */
    public final static String model = "model";

    /**
     * A constant representing the year property in the car advertisement.
     */
    public final static String year = "year";

    /**
     * A constant representing the km property in the car advertisement.
     */
    public final static String km = "km";

// Electricity Advertisements Constants:

    /**
     * A constant representing the condition property in the electricity advertisement.
     */
    public final static String condition = "condition";

    /**
     * A constant representing the electricity type property in the electricity advertisement.
     */
    public final static String electricityType = "electricityType";

    /**
     * The filter button title.
     */
    public final static String filterBtnTitle = "Filter!";
}