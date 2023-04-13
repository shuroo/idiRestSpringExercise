package com.directinsuranceexercise.rest.model;

import com.directinsuranceexercise.rest.utilities.Constants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class AssetAdvertisement extends GenericAdvertisement{

    protected String category = Constants.assetCategory;
    protected Integer assetSize;
    protected Integer numberOfRooms ;

    @NotEmpty
    @Pattern(regexp = "^(sell|rent)$", message = "Asset ad-type must be either 'Sell' or 'Rent'")
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


}
