package com.directinsuranceexercise.rest.model;

import com.directinsuranceexercise.rest.utilities.Constants;

/**
 * An AssetAdvertisement is a type of GenericAdvertisement that represents an asset for sale or rent,
 * such as a house or apartment.
 *
 *  @author shirirave
 *  @since 18/04/2023
 */
public class AssetAdvertisement extends GenericAdvertisement {

    /**
     * The category of this AssetAdvertisement (always "ASSET" in this case).
     */
    protected String category = Constants.assetCategory;

    /**
     * The size of the asset (e.g. the square footage of a house).
     */
    protected Integer assetSize;

    /**
     * The number of rooms in the asset (e.g. the number of bedrooms in a house).
     */
    protected Integer numberOfRooms;

    /**
     * The type of asset advertisement (e.g. "FOR SALE" or "FOR RENT").
     */
    protected String assetAdType;

    /**
     * Returns the type of asset advertisement.
     *
     * @return The type of asset advertisement.
     */
    public String getAssetAdType() {
        return assetAdType;
    }

    /**
     * Sets the type of asset advertisement.
     *
     * @param assetAdType The type of asset advertisement.
     */
    public void setAssetAdType(String assetAdType) {
        this.assetAdType = assetAdType;
    }

    /**
     * Returns the size of the asset.
     *
     * @return The size of the asset.
     */
    public Integer getAssetSize() {
        return assetSize;
    }

    /**
     * Sets the size of the asset.
     *
     * @param assetSize The size of the asset.
     */
    public void setAssetSize(Integer assetSize) {
        this.assetSize = assetSize;
    }

    /**
     * Returns the number of rooms in the asset.
     *
     * @return The number of rooms in the asset.
     */
    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    /**
     * Sets the number of rooms in the asset.
     *
     * @param numberOfRooms The number of rooms in the asset.
     */
    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    /**
     * Default constructor for an AssetAdvertisement.
     */
    public AssetAdvertisement() {
    }

    /**
     * Constructor for an AssetAdvertisement that takes a price, contact name, contact phone number,
     * number of rooms, and asset size.
     *
     * @param price The price of the asset.
     * @param contactName The name of the person to contact for more information about the asset.
     * @param contactPhone The phone number of the person to contact for more information about the asset.
     * @param numberOfRooms The number of rooms in the asset.
     * @param assetSize The size of the asset.
     */
    public AssetAdvertisement(Double price, String contactName, String contactPhone, int numberOfRooms, int assetSize) {
        super(price, contactName, contactPhone);
        this.numberOfRooms = numberOfRooms;
        this.assetSize = assetSize;
    }

}
