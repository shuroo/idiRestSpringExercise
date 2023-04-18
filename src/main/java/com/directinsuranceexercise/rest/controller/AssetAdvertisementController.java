package com.directinsuranceexercise.rest.controller;

import com.directinsuranceexercise.rest.model.AssetAdvertisement;
import com.directinsuranceexercise.rest.model.CRUDAdvertisementInterface;
import com.directinsuranceexercise.rest.utilities.AdvertisementUtils;
import com.directinsuranceexercise.rest.utilities.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/assetAdvertisements", produces = "application/json")
public class AssetAdvertisementController extends AdvertisementController implements CRUDAdvertisementInterface {

    @RequestMapping(value = "/create",
            method = RequestMethod.POST)
    public ResponseEntity<AssetAdvertisement> createAssetAdvertisement(@RequestBody AssetAdvertisement assetAdvertisement) throws Exception {
        return createAdvertisement(Constants.assetCategory, assetAdvertisement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetAdvertisement> updateAdvertisement(@PathVariable("id") String id,
                                                                    @RequestBody AssetAdvertisement assetAdvertisement)
            throws Exception {

        ResponseEntity response = super.preUpdateAdvertisement(id, assetAdvertisement);
        AssetAdvertisement existingAssetAdvertisement = null;
        try {
            existingAssetAdvertisement = (AssetAdvertisement) response.getBody();
        } catch (Exception e) {
            throw new Exception(AdvertisementUtils.createErrorEditMsg(assetAdvertisement.getCategory()));
        }
        if (existingAssetAdvertisement == null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        AdvertisementUtils.setAssetAd(existingAssetAdvertisement , assetAdvertisement);
        allAdvertisements.add(existingAssetAdvertisement);
        return ResponseEntity.ok(existingAssetAdvertisement);
    }

    @GetMapping("/{id}")
    @Override
    public boolean  bringAdvertisementToTop(@PathVariable("id") String id) {
        return super.bringAdvertisementToTop(id);
    }

    @DeleteMapping("/{id}")
    @Override
    public boolean deleteAdvertisement(@PathVariable("id") String id) {
        return super.deleteAdvertisement(id);
    }
}
