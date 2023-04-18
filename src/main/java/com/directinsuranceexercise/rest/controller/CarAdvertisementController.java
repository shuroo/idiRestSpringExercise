package com.directinsuranceexercise.rest.controller;

import com.directinsuranceexercise.rest.model.CRUDAdvertisementInterface;
import com.directinsuranceexercise.rest.model.CarAdvertisement;
import com.directinsuranceexercise.rest.utilities.AdvertisementUtils;
import com.directinsuranceexercise.rest.utilities.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**

 CarAdvertisementController is a RestController for handling HTTP requests related to car advertisements.
 It extends the AdvertisementController and implements the CRUDAdvertisementInterface.
 It is responsible for handling the creation, updating, and deleting of CarAdvertisements.
 All endpoints produce JSON.
 @author shirirave
 @since 04-18-2023
 */

@RestController
@RequestMapping(value = "/carAdvertisements", produces = "application/json")
public class CarAdvertisementController extends AdvertisementController implements CRUDAdvertisementInterface {

    /**
     * This endpoint handles HTTP POST requests for creating a new CarAdvertisement.
     * The method receives a CarAdvertisement object in the request body,
     * and returns a ResponseEntity with a CarAdvertisement object and HTTP status code 200.
     * If an exception is thrown, the method will throw an exception with an error message.
     *
     * @param carAdvertisement - a CarAdvertisement object to be created
     * @return ResponseEntity<CarAdvertisement> - a CarAdvertisement object and HTTP status code 200
     * @throws Exception - if an error occurs while creating the CarAdvertisement
     */
    @RequestMapping(value = "/create",
            method = RequestMethod.POST)
    public ResponseEntity<CarAdvertisement> createCarAdvertisement(@RequestBody CarAdvertisement carAdvertisement) throws Exception {
        return createAdvertisement(Constants.carCategory, carAdvertisement);
    }


    /**
     * This endpoint handles HTTP PUT requests for updating an existing CarAdvertisement.
     * The method receives a CarAdvertisement object in the request body and an id as a path variable,
     * and returns a ResponseEntity with a CarAdvertisement object and HTTP status code 200.
     * If an exception is thrown, the method will throw an exception with an error message.
     *
     * @param id - the id of the CarAdvertisement to be updated
     * @param carAdvertisement - a CarAdvertisement object with updated information
     * @return ResponseEntity<CarAdvertisement> - a CarAdvertisement object and HTTP status code 200
     * @throws Exception - if an error occurs while updating the CarAdvertisement
     */

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


    /**
     * This endpoint handles HTTP GET requests for bringing a CarAdvertisement to the top of the list.
     * The method receives an id as a path variable,
     * and returns a boolean indicating whether the CarAdvertisement was successfully moved to the top of the list.
     *
     * @param id - the id of the CarAdvertisement to be moved to the top
     * @return boolean - true if the CarAdvertisement was successfully moved to the top, false otherwise
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

    /**

     Delete Advertisement -
     Deletes the CarAdvertisement with the given ID from the system.
     @param id - the ID of the CarAdvertisement to delete
     @return boolean - true if the CarAdvertisement was successfully deleted, false otherwise
     */
    @DeleteMapping("/{id}")
    @Override
    public boolean deleteAdvertisement(@PathVariable("id") String id) {
        return super.deleteAdvertisement(id);
    }

}
