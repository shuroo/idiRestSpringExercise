package com.directinsuranceexercise.rest.controller;

import com.directinsuranceexercise.rest.config.AdvertisementConfig;
import com.directinsuranceexercise.rest.model.AdManager;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import com.directinsuranceexercise.rest.utilities.AdvertisementUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    AdvertisementConfig config;
    protected AdManager advertisementManager = AdManager.getInstance();
    protected ConcurrentLinkedQueue<GenericAdvertisement> allAdvertisements = advertisementManager.getAdvertisementsList();

    public ConcurrentLinkedQueue<GenericAdvertisement> getAdvertisementsList() {

        return allAdvertisements;
    }


    protected GenericAdvertisement findById(String requestedId) {
        if (requestedId != null) {

            return allAdvertisements.stream()
                    .filter(ad -> ad.getId().equals(requestedId))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    @GetMapping("/all")
    public ResponseEntity<List<GenericAdvertisement>> getAll() {

        System.out.println(allAdvertisements.size());

        return ResponseEntity.ok(allAdvertisements.stream().toList());
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
    public ResponseEntity<List<GenericAdvertisement>> jumpAssetAdvertisement(@PathVariable("id") String id) {

        for (GenericAdvertisement ad : allAdvertisements) {
            if (ad.getId().equals(id)) {
                allAdvertisements.remove(ad);
                allAdvertisements.add(ad);
                break;
            }
        }
        return ResponseEntity.ok(allAdvertisements.stream().toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericAdvertisement> getAdvertisement(@PathVariable("id") String id) {

        GenericAdvertisement ad = allAdvertisements.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
        return ResponseEntity.ok().body(ad);
    }


    /**
     * Method for before creation of a new Advertisement: make sure the id does not already exists in the system,
     * generate a new id
     * @param ad
     * @return
     */
    protected synchronized GenericAdvertisement preCreateAd(GenericAdvertisement ad) throws Exception {
        AdvertisementUtils.generateAndSetId(ad);
        if (findById(ad.getId()) != null) {
                throw new Exception("The following ID already exists in the system. method is aborting.");
            }
        return ad;
    }



    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<T> createAdvertisement(@RequestBody T advertisement) throws Exception {
        // Generate a new ID for the advertisement and add it to the list
        preCreateAd(advertisement);
        System.out.println("In create");
        allAdvertisements.add((T)advertisement);
        return ResponseEntity.ok().body((T)advertisement);
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

        GenericAdvertisement existingAssetAdvertisement = findById(id);
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
        return allAdvertisements.removeIf(user -> user.getId().equals(id));
    }

    // Asset instance from config file to create upon startup


    // todo: move from here!!!


}

