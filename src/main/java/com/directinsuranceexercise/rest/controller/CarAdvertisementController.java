package com.directinsuranceexercise.rest.controller;

import com.directinsuranceexercise.rest.model.CarAdvertisement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/carAdvertisements", produces = "application/json")
public class CarAdvertisementController extends AdvertisementController<CarAdvertisement> {

    // todo: test carAdvertisements/create

    // todo: change this to use generics.
    @PutMapping("/update/{id}")
    public ResponseEntity<CarAdvertisement> updateAdvertisement(@PathVariable("id") String id,
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
