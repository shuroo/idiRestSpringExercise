package com.directinsuranceexercise.rest.controller;

import com.directinsuranceexercise.rest.model.AdManager;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import com.directinsuranceexercise.rest.utilities.AdvertisementUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

/**
 * Just an abstract controller to implement a basic CRUD on the genericGrid, not to be accessed directly but to be inherited.
 */
@Component
@RestController
abstract class AdvertisementController implements CRUDAdvertisementInterface {

    private static final Logger logger = Logger.getLogger(GenericAdvertisement.class.getName());

    protected AdManager advertisementManager = AdManager.getInstance();

    protected ConcurrentLinkedQueue<GenericAdvertisement> allAdvertisements = advertisementManager.getAdvertisements();
//
//    public ConcurrentLinkedQueue<GenericAdvertisement> getAllAdvertisements() {
//        return allAdvertisements;
//    }
//    @GetMapping("/all")
//    public ResponseEntity<List<GenericAdvertisement>> getAll() {
//        return ResponseEntity.ok(allAdvertisements.stream().toList());
//    }

//    @GetMapping("/filterCategory/{category}")
//    public ResponseEntity<List<GenericAdvertisement>> getByCategory( String category) {
//
//        List<GenericAdvertisement> allByCategory = AdvertisementUtils.filterByCategory(category,allAdvertisements.stream().toList());
//        return ResponseEntity.ok(allByCategory);
//    }

//    @GetMapping("/filterPrice/{price}")
//    public ResponseEntity<List<GenericAdvertisement>> getByMaxPrice(@PathVariable("price") Double maxPrice) {
//        List<GenericAdvertisement> listByMaxPrice = AdvertisementUtils.filterByMaxPrice(maxPrice,allAdvertisements.stream().toList());
//        return ResponseEntity.ok(listByMaxPrice);
//    }

    // todo: need to test this. should we use a set or a list?
    public boolean bringAdvertisementToTop(String id) {

        try {
            AdvertisementUtils.bringToTop(id, allAdvertisements);
        }catch (Exception e){
            logger.warning("Failed to perform operation 'bringToTo'. Error:"+e.getMessage());
            // Better to return status code when possible.
            return false;
        }
        // for success -
        return true;
    }


    /**
     * Method for before creation of a new Advertisement: make sure the id does not already exists in the system,
     * generate a new id
     * @param ad
     * @return
     */

////////////////////////////////////////////////////////////////////


    public ResponseEntity createAdvertisement(String category, GenericAdvertisement advertisement) throws Exception {
        // Generate a new ID for the advertisement and add it to the list
        AdvertisementUtils.generateAndSetId(advertisement);
        advertisement.setCategory(category);

        if (AdvertisementUtils.findById(advertisement.getId(),allAdvertisements) != null) {
            throw new Exception("The following ID already exists in the system. method is aborting.");
        }
        allAdvertisements.add(advertisement);
        return ResponseEntity.ok(advertisement);

    }



    // todo: inherit and split the updates.

    /**
     * Generic method for ad update - cannot be accessed directly (-only for inheritance)
     *
     * @param id
     * @param assetAdvertisement
     * @return
     * @throws Exception
     */
    public ResponseEntity<GenericAdvertisement> updateAdvertisement(
            String id, GenericAdvertisement assetAdvertisement) throws Exception {

        GenericAdvertisement existingAssetAdvertisement = AdvertisementUtils.findById(id,allAdvertisements);
        if (existingAssetAdvertisement == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Such ID does not exists
        }
        existingAssetAdvertisement.setContactName(assetAdvertisement.getContactName());
        existingAssetAdvertisement.setContactPhoneNumber(assetAdvertisement.getContactPhoneNumber());
        existingAssetAdvertisement.setPrice(assetAdvertisement.getPrice());
        existingAssetAdvertisement.setId(assetAdvertisement.getId());
        allAdvertisements.add(existingAssetAdvertisement);
        return ResponseEntity.ok(existingAssetAdvertisement);
    }


    public boolean deleteAdvertisement(String id) {

        try {
            return allAdvertisements.removeIf(ad -> ad.getId().equals(id));
        }catch (Exception e){
            logger.warning("Failed to delete record, Exception detected with msg:"+e.getMessage());
            return false;
        }
    }


}

