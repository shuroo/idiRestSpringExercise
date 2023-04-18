package com.directinsuranceexercise.rest.controller;

import com.directinsuranceexercise.rest.model.CRUDAdvertisementInterface;
import com.directinsuranceexercise.rest.model.CarAdvertisement;
import com.directinsuranceexercise.rest.utilities.AdvertisementUtils;
import com.directinsuranceexercise.rest.utilities.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/carAdvertisements", produces = "application/json")
public class CarAdvertisementController extends AdvertisementController implements CRUDAdvertisementInterface {

    // todo: test carAdvertisements/create
    @RequestMapping(value = "/create",
            method = RequestMethod.POST)
    public ResponseEntity<CarAdvertisement> createCarAdvertisement(@RequestBody CarAdvertisement carAdvertisement) throws Exception {
        return createAdvertisement(Constants.carCategory, carAdvertisement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarAdvertisement> updateAdvertisement(@PathVariable("id") String id,
                                                                @RequestBody CarAdvertisement carAdvertisement)
            throws Exception {

        ResponseEntity response = super.preUpdateAdvertisement(id, carAdvertisement);
        CarAdvertisement existingCarAdvertisement = null;
        try {
            existingCarAdvertisement = (CarAdvertisement) response.getBody();
        } catch (Exception e) {
            throw new Exception("Failed to parse the given id as an Car. please check your params and try again. ");
        }
        if (existingCarAdvertisement == null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        AdvertisementUtils.setCarAd(existingCarAdvertisement, carAdvertisement);
        // no add or remove, this happened in the parent already
        allAdvertisements.add(existingCarAdvertisement);
        return ResponseEntity.ok(existingCarAdvertisement);
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
