package com.directinsuranceexercise.rest.controller;

import com.directinsuranceexercise.rest.model.AssetAdvertisement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/assetAdvertisements", produces = "application/json")
public class AssetAdvertisementController extends AdvertisementController<AssetAdvertisement> {

    @PutMapping("/update/{id}")
    public ResponseEntity<AssetAdvertisement> updateAdvertisement(@PathVariable("id") String id,
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
}
