package com.directinsuranceexercise.rest.model;

import com.directinsuranceexercise.rest.utilities.Constants;

/**
 * Represents an electricity advertisement.
 *
 * @author shirirave
 * @since 18/04/2023
 */
public class ElectricityAdvertisement extends GenericAdvertisement {

    /**
     * The category of the electricity product advertised.
     */
    protected String category = Constants.electricityCategory;

    /**
     * The condition of the electricity product advertised.
     */
    protected String condition;

    /**
     * The type of electricity product advertised.
     */
    protected String electricityType;

    /**
     * Gets the condition of the electricity product advertised.
     *
     * @return The condition of the electricity product advertised.
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Sets the condition of the electricity product advertised.
     *
     * @param condition The condition of the electricity product advertised.
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * Gets the type of electricity product advertised.
     *
     * @return The type of electricity product advertised.
     */
    public String getElectricityType() {
        return electricityType;
    }

    /**
     * Sets the type of electricity product advertised.
     *
     * @param electricityType The type of electricity product advertised.
     */
    public void setElectricityType(String electricityType) {
        this.electricityType = electricityType;
    }
}
