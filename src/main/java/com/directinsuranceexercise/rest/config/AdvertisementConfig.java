package com.directinsuranceexercise.rest.config;

import com.directinsuranceexercise.rest.model.AdManager;
import com.directinsuranceexercise.rest.model.AssetAdvertisement;
import com.directinsuranceexercise.rest.model.CarAdvertisement;
import com.directinsuranceexercise.rest.model.ElectricityAdvertisement;
import com.directinsuranceexercise.rest.utilities.Constants;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

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
    private int assetPrice;

    @Value("${assetAd.assetSize}")
    private int assetSize;

    @Value("${assetAd.assetAdType}")
    private String assetAdType;

    @Value("${assetAd.numberOfRooms}")
    private int numberOfRooms;

    // Sample car config to add to the list on application startup:

    @Value("${carAd.price}")
    private int carPrice;

    @Value("${carAd.model}")
    private String carModel;

    @Value("${carAd.year}")
    private int carYear;

    @Value("${carAd.color}")
    private String carColor;

    @Value("${carAd.carKm}")
    private Integer carKm;

    @Value("${electronicsAd.price}")
    private int electricityPrice;

    @Value("${electronicsAd.condition}")
    private String electricityCondition;

    @Value("${electronicsAd.electricityType}")
    private String electricityType;

    // Create a sample asset ad, based on the configuration file definitions:
    public AssetAdvertisement getSampleAssetAdvertisement() {
        AssetAdvertisement ad = new AssetAdvertisement();
        ad.setCategory(Constants.assetCategory);
        ad.setPrice(assetPrice);
        ad.setContactName(contactName);
        ad.setContactPhoneNumber(contactPhoneNumber);
        ad.setAssetSize(assetSize);
        ad.setAssetAdType(assetAdType);
        ad.setNumberOfRooms(numberOfRooms);
        //todo: advance id, all are created with 0!!
        return ad;
    }

    // Create a sample car ad, based on the configuration file definitions:
    public CarAdvertisement getSampleCarAdvertisement() {
        CarAdvertisement ad = new CarAdvertisement();
        ad.setCategory(Constants.carCategory);
        ad.setPrice(carPrice);
        ad.setContactName(contactName);
        ad.setContactPhoneNumber(contactPhoneNumber);
        ad.setYear(carYear);
        ad.setKm(carKm);
        ad.setModel(carModel);
        return ad;
    }

    // Create a sample electronics ad, based on the configuration file definitions:
    public ElectricityAdvertisement getSampleElectronicsAdvertisement() {
        ElectricityAdvertisement ad = new ElectricityAdvertisement();
        ad.setCategory(Constants.electricityCategory);
        ad.setPrice(electricityPrice);
        ad.setContactName(contactName);
        ad.setContactPhoneNumber(contactPhoneNumber);
        ad.setElectricityType(electricityType);
        ad.setCondition(electricityCondition);
        return ad;
    }

}

