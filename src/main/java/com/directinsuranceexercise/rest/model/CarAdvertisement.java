package com.directinsuranceexercise.rest.model;

import com.directinsuranceexercise.rest.utilities.Constants;

/**

 The CarAdvertisement class represents an advertisement for a car, which is a type of GenericAdvertisement.

 It provides additional attributes specific to cars such as the manufacturer, model, year, kilometers, and color.

 The category field specifies that this is a car advertisement.

 @author shirirave

 @since 04-18-2023
 */
public class CarAdvertisement extends GenericAdvertisement {

    /**

     The manufacturer of the car.
     */
    protected String manufacturer;

    /**

     The model of the car.
     */
    protected String model;

    /**

     The year that the car was made.
     */
    protected int year;

   /**
    * The number of kilometers driven by the car.
    */
    protected int km;

    /**
     * The color of the car.
     */
    protected String color;
    /**

     The category of this advertisement. It is set to "car" in this case.
     */
    protected String category = Constants.carCategory;
    /**

     Gets the manufacturer of the car.
     @return The manufacturer of the car.
     */
    public String getManufacturer() {
        return manufacturer;
    }
    /**

     Sets the manufacturer of the car.
     @param manufacturer The new manufacturer of the car.
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    /**

     Gets the model of the car.
     @return The model of the car.
     */
    public String getModel() {
        return model;
    }
    /**

     Sets the model of the car.
     @param model The new model of the car.
     */
    public void setModel(String model) {
        this.model = model;
    }
    /**

     Gets the year that the car was made.
     @return The year that the car was made.
     */
    public int getYear() {
        return year;
    }
    /**

     Sets the year that the car was made.
     @param year The new year that the car was made.
     */
    public void setYear(int year) {
        this.year = year;
    }
    /**

     Gets the number of kilometers driven by the car.
     @return The number of kilometers driven by the car.
     */
    public int getKm() {
        return km;
    }
    /**

     Sets the number of kilometers driven by the car.
     @param km The new number of kilometers driven by the car.
     */
    public void setKm(int km) {
        this.km = km;
    }
    /**

     Gets the color of the car.
     @return The color of the car.
     */
    public String getColor() {
        return color;
    }
    /**

     Sets the color of the car.
     @param color The new color of the car.
     */
    public void setColor(String color) {
        this.color = color;
    }

}
