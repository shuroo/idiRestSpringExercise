package com.directinsuranceexercise.rest.controller;

import com.directinsuranceexercise.rest.api.CRUDAdvertisementInterface;
import com.directinsuranceexercise.rest.model.ElectricityAdvertisement;
import com.directinsuranceexercise.rest.utilities.AdvertisementUtils;
import com.directinsuranceexercise.rest.utilities.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing electricity advertisements.
 * @author shirirave
 * @since 18/04/2023
 */
@RestController
@RequestMapping(value = "/electricityAdvertisements", produces = "application/json")
public class ElectricityAdvertisementController extends AdvertisementController implements CRUDAdvertisementInterface {

    /**
     * HTTP endpoint for creating a new electricity advertisement.
     * @param electricityAdvertisement The electricity advertisement to be created.
     * @return A ResponseEntity containing the created advertisement and a HTTP status code.
     * @throws Exception If there is an error during the creation process.
     */

    @RequestMapping(value = "/create",
            method = RequestMethod.POST)
    public ResponseEntity<ElectricityAdvertisement> createElectricityAdvertisement(@RequestBody ElectricityAdvertisement electricityAdvertisement) throws Exception {
        return createAdvertisement(Constants.electricityCategory, electricityAdvertisement);
    }

    /**
     * HTTP endpoint for updating an existing electricity advertisement.
     * @param id The ID of the advertisement to be updated.
     * @param electricityAdvertisement The updated electricity advertisement.
     * @return A ResponseEntity containing the updated advertisement and a HTTP status code.
     * @throws Exception If there is an error during the update process.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ElectricityAdvertisement> updateAdvertisement(@PathVariable("id") String id,
                                                                        @RequestBody ElectricityAdvertisement electricityAdvertisement)
            throws Exception {

        ResponseEntity response = super.preUpdateAdvertisement(id, electricityAdvertisement);
        ElectricityAdvertisement existingElectricityAdvertisement = null;
        try {
            existingElectricityAdvertisement = (ElectricityAdvertisement) response.getBody();
        } catch (Exception e) {
            throw new Exception("Failed to parse the given id as an Car. please check your params and try again. ");
        }
        if (existingElectricityAdvertisement == null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        AdvertisementUtils.setElectricityAd(existingElectricityAdvertisement, electricityAdvertisement);
        // no add or remove, this happened in the parent already
        allAdvertisements.add(existingElectricityAdvertisement);
        return ResponseEntity.ok(existingElectricityAdvertisement);
    }

    /**
     * Jump Ad to top -
     * This method was implemented just for the routes consistency,
     * The implementation is exactly the same as in the parent.
     *
     * @param id - the Ad Id
     * @return boolean
     */

    /**
     * HTTP endpoint for bringing an advertisement to the top of the list.
     * This method was implemented just for the sake of route consistency, and its implementation is the same as in the parent class.
     * @param id The ID of the advertisement to be moved to the top.
     * @return true if the advertisement was moved to the top successfully, false otherwise.
     */
    @GetMapping("/{id}")
    @Override
    public boolean bringAdvertisementToTop(@PathVariable("id") String id) {
        return super.bringAdvertisementToTop(id);
    }

    /**
     * Delete Ad -
     * This method was implemented just for the routes consistency,
     * since its implementation already exists in its parent
     *
     * @param id - the Ad Id
     * @return boolean
     */



    /**
     * This method deletes an ElectricityAdvertisement.
     *
     * @param id - the ID of the ElectricityAdvertisement to delete
     * @return boolean - true if the ElectricityAdvertisement was successfully deleted, false otherwise
     */

     @DeleteMapping("/{id}")
        @Override
        public boolean deleteAdvertisement(@PathVariable("id") String id) {
            return super.deleteAdvertisement(id);
        }

}
