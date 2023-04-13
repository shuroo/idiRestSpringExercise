package com.directinsuranceexercise.rest.model;

import com.directinsuranceexercise.rest.config.AdvertisementConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Class to manage the Ads data structure (List) as a singleton
 */
public class AdManager {
    @Autowired
    protected AdvertisementConfig config;
    private static AdManager instance = null;

    // Create an in-memory list to store the GenericAdvertisement objects
    private ConcurrentLinkedQueue<GenericAdvertisement> allAdvertisements;

    private AdManager() {
        allAdvertisements = new ConcurrentLinkedQueue<>();
    }

    public static AdManager getInstance() {
        if (instance == null) {
            instance = new AdManager();
        }
        return instance;
    }

    public ConcurrentLinkedQueue<GenericAdvertisement> getAdvertisements() {
        return allAdvertisements;
    }
}

