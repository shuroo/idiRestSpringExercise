package com.directinsuranceexercise.rest.controller;

import com.directinsuranceexercise.rest.config.AdvertisementConfig;
import com.directinsuranceexercise.rest.model.AssetAdvertisement;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import com.directinsuranceexercise.rest.utilities.Constants;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/assetAdvertisements", produces = "application/json")
public class AssetAdvertisementController extends AdvertisementController {

    // Asset instance from config file to create upon startup
    @Autowired
    private AdvertisementConfig config;

    @RequestMapping(value = "/create",
            method = RequestMethod.POST)
    public ResponseEntity<AssetAdvertisement> createAssetAdvertisement(@RequestBody AssetAdvertisement assetAdvertisement) throws Exception {
        // Generate a new ID for the user and add it to the list
        super.preCreateAd((GenericAdvertisement) assetAdvertisement);
        assetAdvertisement.setCategory(Constants.assetCategory);
        getAdvertisementsList().add(assetAdvertisement);
        return ResponseEntity.ok().body(assetAdvertisement);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<GenericAdvertisement> updateAdvertisement(@PathVariable("id") Long id,
                                                                    @RequestBody AssetAdvertisement assetAdvertisement)
            throws Exception {

        ResponseEntity response = super.updateAdvertisement(id, assetAdvertisement);
        AssetAdvertisement existingAssetAdvertisement = null;
        try {
            existingAssetAdvertisement = (AssetAdvertisement) response.getBody();
        } catch (Exception e) {
            throw new Exception("Failed to parse the given id as an Asset. please check your params and try again. ");
        }
        if (existingAssetAdvertisement == null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        existingAssetAdvertisement.setAssetSize(assetAdvertisement.getAssetSize());
        existingAssetAdvertisement.setAssetAdType(assetAdvertisement.getAssetAdType());
        existingAssetAdvertisement.setNumberOfRooms(assetAdvertisement.getNumberOfRooms());
        allAdvertisements.remove(assetAdvertisement);
        allAdvertisements.add(existingAssetAdvertisement);
        return ResponseEntity.ok(existingAssetAdvertisement);
    }

    @Bean
    public static CommonAnnotationBeanPostProcessor commonAnnotationBeanPostProcessor() {
        return new CommonAnnotationBeanPostProcessor();
    }
    @PostConstruct
    @Bean(initMethod = "init")
    public void init() {
        allAdvertisements.add(config.getSampleAssetAdvertisement());
    }
}
