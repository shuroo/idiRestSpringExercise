package com.directinsuranceexercise.rest.model;

import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import org.springframework.http.ResponseEntity;

/**
 * Markup interface to define which methods needs to be implemented on each advertisement type which should extend this.
 */
public interface CRUDAdvertisementInterface {

    public ResponseEntity createAdvertisement(String category, GenericAdvertisement advertisement) throws Exception;
    public ResponseEntity<GenericAdvertisement> preUpdateAdvertisement(
            String id, GenericAdvertisement assetAdvertisement) throws Exception;


    public boolean deleteAdvertisement(String id);

    /**
     * Jump the ad in its order
     * @return
     */
    public boolean bringAdvertisementToTop(String id);

}
