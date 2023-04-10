package com.directinsuranceexercise.rest.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ElectricityAdvertisement extends GenericAdvertisement {

    @NotEmpty
    @Pattern(regexp = "^(new|used)$", message = "condition must be 'new' or 'used'")
    private String condition;

    @NotEmpty
    @Pattern(regexp = "^(frigider|computer|drone|amplifier|smartphone)$", message = "electricityType must be of a known type, please try again")
    private String electricityType;

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
