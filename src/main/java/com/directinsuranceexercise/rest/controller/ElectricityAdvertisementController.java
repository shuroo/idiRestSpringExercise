package com.directinsuranceexercise.rest.controller;

import com.directinsuranceexercise.rest.model.CarAdvertisement;
import com.directinsuranceexercise.rest.model.ElectricityAdvertisement;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/electricityAds", produces = "application/json")
public class ElectricityAdvertisementController extends AdvertisementController<ElectricityAdvertisement> {


    @PutMapping("/updateElectricity/{id}")
    public ResponseEntity<GenericAdvertisement> updateAdvertisement(@PathVariable("id") String id,
                                                                    @RequestBody ElectricityAdvertisement electricityAdvertisement) throws Exception {

        ResponseEntity response = super.updateAdvertisement(id, (GenericAdvertisement)electricityAdvertisement);
        ElectricityAdvertisement existingAssetAdvertisement = null;
        try {
            existingAssetAdvertisement = (ElectricityAdvertisement) response.getBody();
        } catch (Exception e) {
            throw new Exception("Failed to parse the given id as an Asset. please check your params and try again. ");
        }
        if (existingAssetAdvertisement == null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        existingAssetAdvertisement.setCondition(electricityAdvertisement.getCondition());
        existingAssetAdvertisement.setElectricityType(electricityAdvertisement.getElectricityType());
        allAdvertisements.add(existingAssetAdvertisement);
        return ResponseEntity.ok(existingAssetAdvertisement);
    }

}
