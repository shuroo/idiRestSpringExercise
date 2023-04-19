package com.directinsuranceexercise.rest.api;

import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import org.springframework.http.ResponseEntity;

/**
 * Markup interface to define which methods needs to be implemented on each advertisement type which should extend this.
 */
public interface CRUDAdvertisementInterface {

    public ResponseEntity createAdvertisement(String category, GenericAdvertisement advertisement) throws Exception;

    /**
     * Method for advertisement update.
     *
     * NOTE: THIS SHOULD HAVE ACTUALLY BE A MARKUP METHOD FOR UPDATE, AND NOT PRE-UPDATE.
     * [ BUT I WILL NOT FIX THE ERROR RIGHT NOW ]
     *
     * @param id - the ad id to update
     * @param advertisement - the advertisement to update
     * @return ResponseEntity<GenericAdvertisement>
     *
     */
    public ResponseEntity<GenericAdvertisement> preUpdateAdvertisement(
            String id, GenericAdvertisement advertisement) throws Exception;


    /**
     * Markup method for advertisement delete operation.
     * @param id
     * @return
     */
    public boolean deleteAdvertisement(String id);

    /**
     * Markup method for "Jumping" the ad to the head of the ads list
     * @param id - the ad id.
     * @return boolean - True if succeeded, False otherwise.
     */
    public boolean bringAdvertisementToTop(String id);

}
