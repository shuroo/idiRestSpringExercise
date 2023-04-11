package com.directinsuranceexercise.rest.controller;

import com.directinsuranceexercise.rest.model.AdManager;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import com.directinsuranceexercise.rest.utilities.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/advertisements", produces = "application/json")
public class AdvertisementController {

    // Create an in-memory list to store the User objects
    private AdManager advertisementManager = AdManager.getInstance();

    protected List<GenericAdvertisement> allAdvertisements = advertisementManager.getAdvertisementsList();

    public List<GenericAdvertisement> getAdvertisementsList() {
        return allAdvertisements;
    }


    protected GenericAdvertisement findById(Long requestedId) {
        if (requestedId != null) {

            return allAdvertisements.stream()
                    .filter(ad -> ad.getId() == requestedId)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    @GetMapping("/all")
    public ResponseEntity<List<GenericAdvertisement>> getAll() {

        System.out.println(allAdvertisements.size());

        return ResponseEntity.ok(allAdvertisements);
    }

    @GetMapping("/filterCategory/{category}")
    public ResponseEntity<List<GenericAdvertisement>> getByCategory(@PathVariable("category") String category) {

        List<GenericAdvertisement> allByCategory = allAdvertisements.stream().filter(ad -> ad.getCategory().equals(category)).toList();
        return ResponseEntity.ok(allByCategory);
    }

    @GetMapping("/filterPrice/{price}")
    public ResponseEntity<List<GenericAdvertisement>> getByMaxPrice(@PathVariable("price") Integer maxPrice) {
        List<GenericAdvertisement> listByMaxPrice = allAdvertisements.stream().filter(ad -> ad.getPrice() <= maxPrice).toList();
        return ResponseEntity.ok(listByMaxPrice);
    }

    // todo: need to test this. should we use a set or a list?
    @GetMapping("/jump/{id}")
    public ResponseEntity<List<GenericAdvertisement>> jumpAssetAdvertisement(@PathVariable("id") Long id) {

        for (GenericAdvertisement ad : allAdvertisements) {
            if (ad.getId() == id) {
                allAdvertisements.remove(ad);
                allAdvertisements.add(ad);
                break;
            }
        }
        return ResponseEntity.ok(allAdvertisements.stream().toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericAdvertisement> getAssetAdvertisement(@PathVariable("id") Long id) {

        GenericAdvertisement ad = allAdvertisements.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
        return ResponseEntity.ok().body(ad);
    }


    /**
     * Method to auto generate and set id in the advertisement object.
     *
     * @param - the entity to set id in.
     */
    protected void generateAndSetId(GenericAdvertisement assetAdvertisement) {
        Long id = (long) (allAdvertisements.size() + 1);
        assetAdvertisement.setId(id);
    }

    /**
     * Method for before creation of a new Advertisement: make sure the id does not already exists in the system,
     * generate a new id
     * @param ad
     * @return
     */
    protected synchronized GenericAdvertisement preCreateAd(GenericAdvertisement ad) throws Exception {
        generateAndSetId(ad);
        if (findById(ad.getId()) != null) {
                throw new Exception("The following ID already exists in the system. method is aborting.");
            }
        return ad;
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
            Long id, GenericAdvertisement assetAdvertisement) throws Exception {

        GenericAdvertisement existingAssetAdvertisement = findById(id);
        if (existingAssetAdvertisement == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Such ID does not exists
        }
        existingAssetAdvertisement.setCategory(assetAdvertisement.getCategory());
        existingAssetAdvertisement.setContactName(assetAdvertisement.getContactName());
        existingAssetAdvertisement.setContactPhoneNumber(assetAdvertisement.getContactPhoneNumber());
        existingAssetAdvertisement.setPrice(assetAdvertisement.getPrice());
        existingAssetAdvertisement.setId(assetAdvertisement.getId());
        allAdvertisements.add(existingAssetAdvertisement);
        return ResponseEntity.ok(existingAssetAdvertisement);
    }

    @DeleteMapping("/{id}")
    public boolean deleteAdvertisement(@PathVariable("id") Long id) {
        return allAdvertisements.removeIf(user -> user.getId() == id);
    }
}

