package com.directinsuranceexercise.rest.model;

import jakarta.validation.constraints.NotEmpty;

public class AssetAdvertisement extends GenericAdvertisement{

    protected Integer assetSize;
    protected Integer numberOfRooms ;

    public Integer getAssetSize() {
        return assetSize;
    }

    public void setAssetSize(Integer assetSize) {
        this.assetSize = assetSize;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }


}
