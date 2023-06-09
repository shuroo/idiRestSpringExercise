package com.directinsuranceexercise.rest.config;

import com.directinsuranceexercise.rest.model.AdManager;
import com.directinsuranceexercise.rest.model.AssetAdvertisement;
import com.directinsuranceexercise.rest.model.CarAdvertisement;
import com.directinsuranceexercise.rest.model.ElectricityAdvertisement;
import com.directinsuranceexercise.rest.utilities.AdvertisementUtils;
import com.directinsuranceexercise.rest.utilities.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AdvertisementConfig {

    AdManager adManager = AdManager.getInstance();

    // General config to be used in the below sample objects (we could also define is separately, like price):
    @Value("${generalConfig.contactName}")
    private String contactName;

    @Value("${generalConfig.contactPhoneNumber}")
    private String contactPhoneNumber;

    // Sample asset config to add to the list on application startup:

    @Value("${assetAd.price}")
    private double assetPrice;

    @Value("${assetAd.assetSize}")
    private int assetSize;

    @Value("${assetAd.assetAdType}")
    private String assetAdType;

    @Value("${assetAd.numberOfRooms}")
    private int numberOfRooms;

    // Sample car config to add to the list on application startup:

    @Value("${carAd.price}")
    private double carPrice;

    @Value("${carAd.model}")
    private String carModel;

    @Value("${carAd.year}")
    private int carYear;

    @Value("${carAd.color}")
    private String carColor;

    @Value("${carAd.manifacturer}")
    private String manifacturer;

    @Value("${carAd.carKm}")
    private Integer carKm;

    @Value("${electronicsAd.price}")
    private double electricityPrice;

    @Value("${electronicsAd.condition}")
    private String electricityCondition;

    @Value("${electronicsAd.electricityType}")
    private String electricityType;

    /**
     * Method for creation of an asset-advertisement on startup
     * Create a sample asset ad, based on the configuration file definitions
     * @return AssetAdvertisement - the asset created
     */
    public AssetAdvertisement getSampleAssetAdvertisement() {
        AssetAdvertisement ad = new AssetAdvertisement();
        AdvertisementUtils.generateAndSetId(ad);
        ad.setCategory(Constants.assetCategory);
        ad.setPrice(assetPrice);
        ad.setContactName(contactName);
        ad.setContactPhoneNumber(contactPhoneNumber);
        ad.setAssetSize(assetSize);
        ad.setAssetAdType(assetAdType);
        ad.setNumberOfRooms(numberOfRooms);
        AdvertisementUtils.generateAndSetId(ad);
        return ad;
    }

    /**
     * Method for creation of a car-advertisement on startup
     * Creates a sample car ad, based on the configuration file definitions:
     * @return CarAdvertisement object - the car-ad created
     */
    public CarAdvertisement getSampleCarAdvertisement() {
        CarAdvertisement ad = new CarAdvertisement();
        AdvertisementUtils.generateAndSetId(ad);
        ad.setCategory(Constants.carCategory);
        ad.setPrice(carPrice);
        ad.setContactName(contactName);
        ad.setContactPhoneNumber(contactPhoneNumber);
        ad.setYear(carYear);
        ad.setKm(carKm);
        ad.setModel(carModel);
        ad.setColor(carColor);
        ad.setManufacturer(manifacturer);
        return ad;
    }

    //

    /**
     * Method for creation of a electricity-advertisement on startup
     * Create a sample electronics ad, based on the configuration file definitions
     * @return ElectricityAdvertisement object - the electricity-ad created
     */
    public ElectricityAdvertisement getSampleElectronicsAdvertisement() {
        ElectricityAdvertisement ad = new ElectricityAdvertisement();
        AdvertisementUtils.generateAndSetId(ad);
        ad.setCategory(Constants.electricityCategory);
        ad.setPrice(electricityPrice);
        ad.setContactName(contactName);
        ad.setContactPhoneNumber(contactPhoneNumber);
        ad.setElectricityType(electricityType);
        ad.setCondition(electricityCondition);
        return ad;
    }

}

