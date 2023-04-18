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

//todo: fix documentation!!!
/**
 * This is an abstract controller class that provides basic CRUD operations for a generic advertisement.
 * It cannot be accessed directly but should be inherited by a concrete controller class.
 *
 * @author shirirave
 * @since 04-18-2023
 */
@Component
@RestController
abstract class AdvertisementController implements CRUDAdvertisementInterface {

    private static final Logger logger = Logger.getLogger(GenericAdvertisement.class.getName());

    // Get the instance of AdManager that manages all advertisements
    protected AdManager advertisementManager = AdManager.getInstance();

    // Get the list of all advertisements from AdManager
    protected ConcurrentLinkedQueue<GenericAdvertisement> allAdvertisements = advertisementManager.getAdvertisements();

    /**
     * This method brings the advertisement with the given id to the top of the list.
     * @param id the id of the advertisement to bring to the top
     * @return true if the operation was successful, false otherwise
     */
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
     * @param advertisement
     * @param category
     * @return
     */


    /**
     * This method creates a new advertisement with a generated id and adds it to the list of all advertisements.
     * @param category the category of the advertisement
     * @param advertisement the advertisement to be created
     * @return ResponseEntity representing the created advertisement
     * @throws Exception if the advertisement already exists in the system
     */
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

    /**
     * Generic method for ad update - cannot be accessed directly (-only by inheritance)
     *
     * @param id
     * @param assetAdvertisement
     * @return
     * @throws Exception
     */


    /**
     * This is a generic method for updating an advertisement, and cannot be accessed directly but only by inheritance.
     * @param id the id of the advertisement to be updated
     * @param assetAdvertisement the updated advertisement object
     * @return ResponseEntity representing the updated advertisement
     * @throws Exception if the id does not exist or the old advertisement cannot be removed
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


/**
 * This method deletes the advertisement with the given id from the list of all advertisements.
 * @param id the id of the advertisement to be deleted
 * @return true if the advertisement was successfully deleted, false otherwise
 *
 */

public boolean deleteAdvertisement(String id) {

        try {
            return allAdvertisements.removeIf(ad -> ad.getId().equals(id));
        } catch (Exception e) {
            logger.warning("Failed to delete record, Exception detected with msg:" + e.getMessage());
            return false;
        }
    }


}

