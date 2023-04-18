package com.directinsuranceexercise.rest.controller;

import com.directinsuranceexercise.rest.model.AdManager;
import com.directinsuranceexercise.rest.model.CRUDAdvertisementInterface;
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

    // todo: need to test this. should we use a set or a list?
    public synchronized boolean bringAdvertisementToTop(String id) {

        try {
            AdvertisementUtils.bringToTop(id, allAdvertisements);
        } catch (Exception e) {
            logger.warning("Failed to perform operation 'bringToTo'. Error:" + e.getMessage());
            // Better to return status code when possible.
            return false;
        }
        // for success -
        return true;
    }


    /**
     * Method for before creation of a new Advertisement: make sure the id does not already exists in the system,
     * generate a new id
     *
     * @param ad
     * @return
     */

////////////////////////////////////////////////////////////////////
    public ResponseEntity createAdvertisement(String category, GenericAdvertisement advertisement) throws Exception {
        // Generate a new ID for the advertisement and add it to the list
        AdvertisementUtils.generateAndSetId(advertisement);
        advertisement.setCategory(category);

        if (AdvertisementUtils.findById(advertisement.getId(), allAdvertisements) != null) {
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
    public synchronized ResponseEntity<GenericAdvertisement> preUpdateAdvertisement(
            String id, GenericAdvertisement assetAdvertisement) throws Exception {

        GenericAdvertisement existingAssetAdvertisement = AdvertisementUtils.findById(id, allAdvertisements);
        if (existingAssetAdvertisement == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Such ID does not exists
        }
        existingAssetAdvertisement.setContactName(assetAdvertisement.getContactName());
        existingAssetAdvertisement.setContactPhoneNumber(assetAdvertisement.getContactPhoneNumber());
        existingAssetAdvertisement.setPrice(assetAdvertisement.getPrice());
        existingAssetAdvertisement.setId(assetAdvertisement.getId());
        // Remove the old advertisement by its id ( should be synced )
        if (!deleteAdvertisement(id)) {
            logger.warning("Failed to remove the old advertisement of id:" + id + ", hence the update failed. aborting");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(existingAssetAdvertisement);
    }


    public boolean deleteAdvertisement(String id) {

        try {
            return allAdvertisements.removeIf(ad -> ad.getId().equals(id));
        } catch (Exception e) {
            logger.warning("Failed to delete record, Exception detected with msg:" + e.getMessage());
            return false;
        }
    }


}

