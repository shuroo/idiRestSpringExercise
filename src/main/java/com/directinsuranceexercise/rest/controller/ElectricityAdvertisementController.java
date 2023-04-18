package com.directinsuranceexercise.rest.controller;

import com.directinsuranceexercise.rest.model.CRUDAdvertisementInterface;
import com.directinsuranceexercise.rest.model.ElectricityAdvertisement;
import com.directinsuranceexercise.rest.utilities.AdvertisementUtils;
import com.directinsuranceexercise.rest.utilities.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/electricityAdvertisements", produces = "application/json")
public class ElectricityAdvertisementController extends AdvertisementController implements CRUDAdvertisementInterface {


    @RequestMapping(value = "/create",
            method = RequestMethod.POST)
    public ResponseEntity<ElectricityAdvertisement> createElectricityAdvertisement(@RequestBody ElectricityAdvertisement electricityAdvertisement) throws Exception {
        return createAdvertisement(Constants.electricityCategory, electricityAdvertisement);
    }

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
    @GetMapping("/{id}")
    @Override
    public boolean bringAdvertisementToTop(@PathVariable("id") String id) {
        return super.bringAdvertisementToTop(id);
    }

    /**
     * Delete Ad -
     * This method was implemented just for the routes,
     * This method was implemented just for the routes consistency,
     *
     * @param id - the Ad Id
     * @return boolean
     */
    @DeleteMapping("/{id}")
    @Override
    public boolean deleteAdvertisement(@PathVariable("id") String id) {
        return super.deleteAdvertisement(id);
    }

}
