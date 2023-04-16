package com.directinsuranceexercise.rest.controller;

import com.directinsuranceexercise.rest.model.ElectricityAdvertisement;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import com.directinsuranceexercise.rest.utilities.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/electricityAdvertisements", produces = "application/json")
public class ElectricityAdvertisementController extends AdvertisementController<ElectricityAdvertisement> {


    @RequestMapping(value = "/create",
            method = RequestMethod.POST)
    public ResponseEntity<ElectricityAdvertisement> createCarAdvertisement(@RequestBody ElectricityAdvertisement electricityAdvertisement) throws Exception {
        return createAdvertisement(Constants.carCategory, electricityAdvertisement);
    }
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
