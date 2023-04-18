package com.directinsuranceexercise.rest.model;

import com.directinsuranceexercise.rest.utilities.Constants;

public class AssetAdvertisement extends GenericAdvertisement {

    protected String category = Constants.assetCategory;
    protected Integer assetSize;
    protected Integer numberOfRooms;
    protected String assetAdType;

    public String getAssetAdType() {
        return assetAdType;
    }

    public void setAssetAdType(String assetAdType) {
        this.assetAdType = assetAdType;
    }

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

    public AssetAdvertisement() {
    }

    public AssetAdvertisement(Double price, String contactName, String contactPhone, int numberOfRooms, int assetSize) {
        super(price, contactName, contactPhone);
        this.numberOfRooms = numberOfRooms;
        this.assetSize = assetSize;
    }

}
