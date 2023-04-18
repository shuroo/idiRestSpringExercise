package com.directinsuranceexercise.rest.model;

import com.directinsuranceexercise.rest.config.AdvertisementConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * The AdManager class manages a list of advertisements as a singleton using the ConcurrentLinkedQueue data structure.
 *
 * @author shirirave
 * @since 04-18-2023
 */
public class AdManager {

    /**
     * The AdvertisementConfig object used to configure the application's advertisements.
     */
    @Autowired
    protected AdvertisementConfig config;

    /**
     * The singleton instance of the AdManager class.
     */
    private static AdManager instance = null;

    /**
     * An in-memory list to store the GenericAdvertisement objects.
     */
    private ConcurrentLinkedQueue<GenericAdvertisement> allAdvertisements;

    /**
     * The AdManager class has a private constructor to prevent external instantiation.
     * It initializes an empty ConcurrentLinkedQueue of GenericAdvertisement objects.
     */
    private AdManager() {
        allAdvertisements = new ConcurrentLinkedQueue<>();
    }

    /**
     * Returns the singleton instance of the AdManager class.
     * If an instance does not exist, a new instance is created.
     *
     * @return The singleton instance of the AdManager class.
     */
    public static AdManager getInstance() {
        if (instance == null) {
            instance = new AdManager();
        }
        return instance;
    }

    /**
     * Returns the ConcurrentLinkedQueue of GenericAdvertisement objects.
     *
     * @return The ConcurrentLinkedQueue of GenericAdvertisement objects.
     */
    public ConcurrentLinkedQueue<GenericAdvertisement> getAdvertisements() {
        return allAdvertisements;
    }
}
