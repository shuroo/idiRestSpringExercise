package com.directinsuranceexercise.rest.model;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to manage the Ads data structure (List) as a singleton
 */
public class AdManager {
    private static AdManager instance = null;

    // Create an in-memory list to store the GenericAdvertisement objects
    private List<GenericAdvertisement> allAdvertisements;

    private AdManager() {
        allAdvertisements = new ArrayList<>();
    }

    public static AdManager getInstance() {
        if (instance == null) {
            instance = new AdManager();
        }
        return instance;
    }

    public List<GenericAdvertisement> getAdvertisementsList() {
        return allAdvertisements;
    }

    public GenericAdvertisement findById(Long requestedId) {
        if (requestedId != null) {
            return allAdvertisements.stream()
                    .filter(ad -> ad.getId() == requestedId)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    protected void generateAndSetId(GenericAdvertisement assetAdvertisement) {
        Long id = (long) (allAdvertisements.size() + 1);
        assetAdvertisement.setId(id);
    }

    // Other methods for manipulating the allAdvertisements list...
}
