package com.directinsuranceexercise.rest.utilities;

import com.directinsuranceexercise.rest.model.GenericAdvertisement;

import java.util.UUID;

/**
 * Utility class to generate a new unique ID in an Ad and set it.
 */
public class AdvertisementUtils {

    /**
     * Method to auto generate and set id in the advertisement object.
     *
     * @param - the entity to set id in.
     */
    public static void generateAndSetId(GenericAdvertisement advertisement) {
        // Generate a new ID for the advertisement, use UUID to ensure uniqueness
        String id = UUID.randomUUID().toString();
        advertisement.setId(id);
    }
}
