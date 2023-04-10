package com.directinsuranceexercise.rest.controller;

import com.directinsuranceexercise.rest.model.AssetAdvertisement;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/assetAds", produces = "application/json")
public class AssetAdvertisementController extends AdvertisementController {

    @RequestMapping(value = "/createAsset",
            method = RequestMethod.POST)
    public ResponseEntity<AssetAdvertisement> createAssetAdvertisement(@RequestBody AssetAdvertisement assetAdvertisement) {
        // Generate a new ID for the user and add it to the list
        generateAndSetId(assetAdvertisement);
        allAdvertisements.add(assetAdvertisement);
        return ResponseEntity.ok().body(assetAdvertisement);
    }


    @PutMapping("/updateAsset/{id}")
    public ResponseEntity<GenericAdvertisement> updateAdvertisement(@PathVariable("id") Long id,
                                                                    @RequestBody AssetAdvertisement assetAdvertisement) throws Exception {

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
        allAdvertisements.add(existingAssetAdvertisement);
        return ResponseEntity.ok(existingAssetAdvertisement);
    }
}
