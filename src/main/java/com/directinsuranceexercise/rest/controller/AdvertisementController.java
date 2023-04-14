package com.directinsuranceexercise.rest.controller;

import com.directinsuranceexercise.rest.model.AdManager;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import com.directinsuranceexercise.rest.utilities.AdvertisementUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
@RestController
@RequestMapping(value = "/advertisements", produces = "application/json")
public class AdvertisementController<T extends GenericAdvertisement> {

    protected AdManager advertisementManager = AdManager.getInstance();

    protected ConcurrentLinkedQueue<GenericAdvertisement> allAdvertisements = advertisementManager.getAdvertisements();

    public ConcurrentLinkedQueue<GenericAdvertisement> getAllAdvertisements() {
        return allAdvertisements;
    }
    @GetMapping("/all")
    public ResponseEntity<List<GenericAdvertisement>> getAll() {
        return ResponseEntity.ok(allAdvertisements.stream().toList());
    }

    @GetMapping("/filterCategory/{category}")
    public ResponseEntity<List<GenericAdvertisement>> getByCategory(@PathVariable("category") String category) {

        List<GenericAdvertisement> allByCategory = AdvertisementUtils.filterByCategory(category,allAdvertisements.stream().toList());
        return ResponseEntity.ok(allByCategory);
    }

    @GetMapping("/filterPrice/{price}")
    public ResponseEntity<List<GenericAdvertisement>> getByMaxPrice(@PathVariable("price") Double maxPrice) {
        List<GenericAdvertisement> listByMaxPrice = AdvertisementUtils.filterByMaxPrice(maxPrice,allAdvertisements.stream().toList());
        return ResponseEntity.ok(listByMaxPrice);
    }

    // todo: need to test this. should we use a set or a list?
    @GetMapping("/bringToTop/{id}")
    public ResponseEntity<List<GenericAdvertisement>> bringAdvertisementToTop(@PathVariable("id") String id) {

        AdvertisementUtils.bringToTop(id,allAdvertisements);
        return ResponseEntity.ok(allAdvertisements.stream().toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericAdvertisement> getAdvertisement(@PathVariable("id") String id) {

        GenericAdvertisement ad = AdvertisementUtils.findById(id,allAdvertisements);
        return ResponseEntity.ok().body(ad);
    }


    /**
     * Method for before creation of a new Advertisement: make sure the id does not already exists in the system,
     * generate a new id
     * @param ad
     * @return
     */

////////////////////////////////////////////////////////////////////


    protected ResponseEntity<T> createAdvertisement(String category, T advertisement) throws Exception {
        // Generate a new ID for the advertisement and add it to the list
        AdvertisementUtils.generateAndSetId(advertisement);

        if (AdvertisementUtils.findById(advertisement.getId(),allAdvertisements) != null) {
            throw new Exception("The following ID already exists in the system. method is aborting.");
        }
        allAdvertisements.add((T)advertisement);
        return ResponseEntity.ok((T)advertisement);

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
    protected ResponseEntity<GenericAdvertisement> updateAdvertisement(
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

    @DeleteMapping("/{id}")
    public boolean deleteAdvertisement(@PathVariable("id") String id) {

        return allAdvertisements.removeIf(ad -> ad.getId().equals(id));
    }


}

