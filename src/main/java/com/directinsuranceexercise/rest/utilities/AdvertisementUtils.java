package com.directinsuranceexercise.rest.utilities;

import com.directinsuranceexercise.rest.model.AssetAdvertisement;
import com.directinsuranceexercise.rest.model.CarAdvertisement;
import com.directinsuranceexercise.rest.model.ElectricityAdvertisement;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;



/**
 *  AdvertisementUtils is a utility class for various operations related to advertisements.
 *  It provides methods for generating a new unique ID, filtering advertisements by category and maximum price,
 *  converting advertisements to specific types, updating existing advertisements with input fields,
 *  and performing 'jump' by ID to bring an advertisement to the top of the list.
 *
 *  @author shirirave
 *  @since 18/04/2023
 */
public class AdvertisementUtils {

    private static final java.util.logging.Logger logger = Logger.getLogger(AdvertisementUtils.class.getName());


    /**
     * Brings an advertisement with the given ID to the top of the list
     * AKA, Method to perform 'jump' by id operation.
     *
     * @param id - the ID of the advertisement to bring to the top
     * @param allAdvertisements - the list of all advertisements
     */
    public static void bringToTop(String id, ConcurrentLinkedQueue<GenericAdvertisement> allAdvertisements) {

        for (GenericAdvertisement ad : allAdvertisements) {
            if (ad.getId().equals(id)) {
                allAdvertisements.remove(ad);
                allAdvertisements.add(ad);
                break;
            }
        }
    }

    /**
     * Filters a list of advertisements by the advertisement category.
     *
     * @param category - The category to filter by ( selected by the user )
     * @param allAdvertisements - the list of all advertisements
     * @return a new list containing only advertisements that match the given category
     */

    public static List<GenericAdvertisement> filterByCategory(String category, List<GenericAdvertisement> allAdvertisements) {
        return allAdvertisements.stream().filter(ad -> ad.getCategory().equals(category)).toList();
    }

    /**
     * Filters a list of advertisements by the maximum price provided by the user input.
     *
     * @param maxPrice - the maximum price to filter by
     * @param allAdvertisements - the list of all advertisements
     * @return a new list containing only advertisements with a price less than or equal to the given maximum price
     */

    public static List<GenericAdvertisement> filterByMaxPrice(Double maxPrice, List<GenericAdvertisement> allAdvertisements) {
        if (maxPrice == null) {
            return null;
        }
        return allAdvertisements.stream().filter(ad -> ad.getPrice() <= maxPrice).toList();
    }

    /**
     * Converts a list of advertisements to a list of AssetAdvertisements.
     *
     * @param ads - the list of advertisements to convert
     * @return a new list containing only AssetAdvertisements
     */

    public static List<AssetAdvertisement> convertToAssetAds(List<GenericAdvertisement> ads) {
        List<AssetAdvertisement> assetAds = null;
        try {
            assetAds = ads.stream().map(ad -> (AssetAdvertisement) ad).toList();
        } catch (Exception e) {
            logger.warning("Failed to convert list from generic ads to asset ads. please check the data. first ad id was:" + ads.get(0).getId());
        }

        return assetAds;
    }

    /**
     * Creates an error message for when an ID cannot be parsed as the expected advertisement type.
     *
     * @param category - the expected advertisement type
     * @return an error message explaining the problem
     */
    public static String createErrorEditMsg(String category) {
        return "Failed to parse the given id as an " + category + ". Make sure that the category matches the required advertisement type";
    }

    /**
     * Converts a list of GenericAdvertisement objects to a list of CarAdvertisement objects.
     * @param ads - the list of GenericAdvertisement objects to convert
     * @return a list of CarAdvertisement objects
     */
    public static List<CarAdvertisement> convertToCarAds(List<GenericAdvertisement> ads) {
        List<CarAdvertisement> catAds = null;
        try {
            catAds = ads.stream().map(ad -> (CarAdvertisement) ad).toList();
        } catch (Exception e) {
            logger.warning("Failed to convert list from generic ads to car ads. please check the data. first ad id was:" + ads.get(0).getId());
        }

        return catAds;
    }

    /**
     * Converts a list of GenericAdvertisement objects to a list of ElectricityAdvertisement objects.
     * @param ads - the list of GenericAdvertisement objects to convert
     * @return a list of ElectricityAdvertisement objects
     */
    public static List<ElectricityAdvertisement> convertToElectronicAds(List<GenericAdvertisement> ads) {
        List<ElectricityAdvertisement> electsAds = null;
        try {
            electsAds = ads.stream().map(ad -> (ElectricityAdvertisement) ad).toList();
        } catch (Exception e) {
            logger.warning("Failed to convert list from generic ads to car ads. please check the data. first ad id was:" + ads.get(0).getId());
        }

        return electsAds;
    }

    /**
     * Method to auto generate and set id in the advertisement object.
     * @param - the entity to set id in.
     */
    public static void generateAndSetId(GenericAdvertisement advertisement) {
        // Generate a new ID for the advertisement, use UUID to ensure uniqueness
        String id = createId();
        advertisement.setId(id);
    }

    /**
     * create a unique id as a new UUID string.
     * @return a unique id - as a string representation of a UUID
     */
    public static String createId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Searches for a GenericAdvertisement object by its ID in a .
     * Find an advertisement object by its id in our data structure
     * ( - The list of ads, represented by the ConcurrentLinkedQueue ), if present
     * @param id - the ID of the advertisement to search for
     * @param allAdvertisements - the queue containing all advertisements
     * @return the found advertisement object, or null if not found
     */
    public static GenericAdvertisement findById(String id, ConcurrentLinkedQueue<GenericAdvertisement> allAdvertisements) {
        GenericAdvertisement foundAd = allAdvertisements.stream()
                .filter(ad -> {
                    return ad.getId().equals(id);
                })
                .findFirst()
                .orElse(null);
        // May be null. return the result.
        if (foundAd == null) {
            logger.warning("ID:" + id + " in method 'findById' was not found. returning null.");
        }
        return foundAd;
    }

    /**
     * Method to update an existing car advertisement with input fields fetched from input entity
     *
     * @param existingCarAdvertisement - The existing advertisement
     * @param carAdvertisementInput-   The input advertisement
     */
    public static void setCarAd(CarAdvertisement existingCarAdvertisement, CarAdvertisement carAdvertisementInput) {
        existingCarAdvertisement.setColor(carAdvertisementInput.getColor());
        existingCarAdvertisement.setKm(carAdvertisementInput.getKm());
        existingCarAdvertisement.setManufacturer(carAdvertisementInput.getManufacturer());
        existingCarAdvertisement.setModel(carAdvertisementInput.getModel());
        existingCarAdvertisement.setYear(carAdvertisementInput.getYear());
    }

    /**
     * Method to update an existing asset advertisement with input fields fetched from input entity
     *
     * @param existingAssetAdvertisement - The existing advertisement
     * @param assetAdvertisementInput-   The input advertisement
     */
    public static void setAssetAd(AssetAdvertisement existingAssetAdvertisement, AssetAdvertisement assetAdvertisementInput) {
        existingAssetAdvertisement.setAssetSize(assetAdvertisementInput.getAssetSize());
        existingAssetAdvertisement.setAssetAdType(assetAdvertisementInput.getAssetAdType());
        existingAssetAdvertisement.setNumberOfRooms(assetAdvertisementInput.getNumberOfRooms());
    }


    /**
     * Method to update an existing electricity advertisement with input fields fetched from input entity
     *
     * @param existingElectricityAdvertisement - The existing advertisement
     * @param electricityAdvertisementInput-   The input advertisement
     */
    public static void setElectricityAd(ElectricityAdvertisement existingElectricityAdvertisement, ElectricityAdvertisement electricityAdvertisementInput) {
        existingElectricityAdvertisement.setCondition(electricityAdvertisementInput.getCondition());
        existingElectricityAdvertisement.setElectricityType(electricityAdvertisementInput.getElectricityType());
    }

    /**
     * General method for exception handling ( with notifications etc )
     *
     * @param e - The Exception thrown
     * @ String - The Error message
     */
    public static String generalErrorMsg(Exception e) {
        return "Error detected:" + e.getMessage() + ".Aborting, Please try again";
    }
}
