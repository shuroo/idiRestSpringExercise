package com.directinsuranceexercise.rest.config;

import com.directinsuranceexercise.rest.controller.AdvertisementController;
import com.directinsuranceexercise.rest.model.AssetAdvertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:application.properties")
public class AdvertisementConfig {

    // Sample asset config to add to the list on application startup:
    @Value("${assetAd.category}")
    private String assetCategory;

    @Value("${assetAd.price}")
    private int assetPrice;

    @Value("${assetAd.contactName}")
    private String contactName;

    @Value("${assetAd.contactPhoneNumber}")
    private String contactPhoneNumber;

    @Value("${assetAd.assetSize}")
    private int assetSize;

    @Value("${assetAd.assetAdType}")
    private String assetAdType;

    @Value("${assetAd.numberOfRooms}")
    private int numberOfRooms;

    // Sample car config to add to the list on application startup:
    @Value("${assetAd.category}")
    private String assetCategory;

    @Value("${assetAd.price}")
    private int assetPrice;

    @Value("${assetAd.contactName}")
    private String contactName;

    @Value("${assetAd.contactPhoneNumber}")
    private String contactPhoneNumber;

    @Value("${assetAd.assetSize}")
    private int assetSize;

    @Value("${assetAd.assetAdType}")
    private String assetAdType;

    @Value("${assetAd.numberOfRooms}")
    private int numberOfRooms;


    // Create a sample asset based on the configuration file definitions:
    public AssetAdvertisement getSampleAssetAdvertisement() {
        AssetAdvertisement ad = new AssetAdvertisement();
        ad.setCategory(assetCategory);
        ad.setPrice(assetPrice);
        ad.setContactName(contactName);
        ad.setContactPhoneNumber(contactPhoneNumber);
        ad.setAssetSize(assetSize);
        ad.setAssetAdType(assetAdType);
        ad.setNumberOfRooms(numberOfRooms);
        return ad;
    }
}

