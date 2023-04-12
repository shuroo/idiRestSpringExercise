package com.directinsuranceexercise.rest.controller;

import com.directinsuranceexercise.rest.model.CarAdvertisement;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import com.directinsuranceexercise.rest.utilities.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/carAdvertisements", produces = "application/json")
public class CarAdvertisementController  extends AdvertisementController {

    // Car instance from config file to create upon startup

    @RequestMapping(value = "/create",
            method = RequestMethod.POST)
    public ResponseEntity<CarAdvertisement> createCarAdvertisement(@RequestBody CarAdvertisement CarAdvertisement) throws Exception {
        // Generate a new ID for the user and add it to the list
        super.preCreateAd((GenericAdvertisement) CarAdvertisement);
        CarAdvertisement.setCategory(Constants.carCategory);
        getAdvertisementsList().add(CarAdvertisement);
        return ResponseEntity.ok().body(CarAdvertisement);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<GenericAdvertisement> updateAdvertisement(@PathVariable("id") Long id,
                                                                    @RequestBody CarAdvertisement carAdvertisement)
            throws Exception {

        ResponseEntity response = super.updateAdvertisement(id, carAdvertisement);
        CarAdvertisement existingCarAdvertisement = null;
        try {
            existingCarAdvertisement = (CarAdvertisement) response.getBody();
        } catch (Exception e) {
            throw new Exception("Failed to parse the given id as an Car. please check your params and try again. ");
        }
        if (existingCarAdvertisement == null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        existingCarAdvertisement.setColor(carAdvertisement.getColor());
        existingCarAdvertisement.setKm(carAdvertisement.getKm());
        existingCarAdvertisement.setManufacturer(carAdvertisement.getManufacturer());
        existingCarAdvertisement.setModel(carAdvertisement.getModel());
        existingCarAdvertisement.setYear(carAdvertisement.getYear());
        //todo: test edit method. should we synch that?
        allAdvertisements.remove(carAdvertisement);
        allAdvertisements.add(existingCarAdvertisement);
        return ResponseEntity.ok(existingCarAdvertisement);
    }

}
