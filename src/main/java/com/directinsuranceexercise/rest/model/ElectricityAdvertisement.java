package com.directinsuranceexercise.rest.model;

import com.directinsuranceexercise.rest.utilities.Constants;

public class ElectricityAdvertisement extends GenericAdvertisement {

    // todo: will it work? test the categories!
    protected String category = Constants.electricityCategory;
    protected String condition;
    protected String electricityType;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getElectricityType() {
        return electricityType;
    }

    public void setElectricityType(String electricityType) {
        this.electricityType = electricityType;
    }
}
