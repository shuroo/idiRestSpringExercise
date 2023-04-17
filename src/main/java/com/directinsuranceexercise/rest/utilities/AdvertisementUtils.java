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
 * Utility class to generate a new unique ID in an Ad and set it.
 */
public class AdvertisementUtils {

    private static final java.util.logging.Logger logger = Logger.getLogger(AdvertisementUtils.class.getName());



    /**
     * Method to perform 'jump' by id - to the top of the list
     * @param id
     * @param allAdvertisements
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
    public static List<GenericAdvertisement> filterByCategory(String category, List<GenericAdvertisement> allAdvertisements){
        return allAdvertisements.stream().filter(ad -> ad.getCategory().equals(category)).toList();
    }

    public static List<GenericAdvertisement> filterByMaxPrice(Double maxPrice, List<GenericAdvertisement> allAdvertisements){
        if (maxPrice == null){
            return null;
        }
        return allAdvertisements.stream().filter(ad -> ad.getPrice()<= maxPrice).toList();
    }

    public static List<AssetAdvertisement> convertToAssetAds(List<GenericAdvertisement> ads){
        List<AssetAdvertisement> assetAds = null;
        try{
            assetAds = ads.stream().map(ad -> (AssetAdvertisement) ad).toList();
        }
        catch (Exception e){
            logger.warning("Failed to convert list from generic ads to asset ads. please check the data. first ad id was:"+ads.get(0).getId());
        }

        return assetAds;
    }

    public static String createErrorEditMsg(String category){
        return "Failed to parse the given id as an "+category+". Make sure that the category matches the required advertisement type";
    }

    public static List<CarAdvertisement> convertToCarAds(List<GenericAdvertisement> ads){
        List<CarAdvertisement> catAds = null;
        try{
            catAds = ads.stream().map(ad -> (CarAdvertisement) ad).toList();
        }
        catch (Exception e){
            logger.warning("Failed to convert list from generic ads to car ads. please check the data. first ad id was:"+ads.get(0).getId());
        }

        return catAds;
    }

    public static List<ElectricityAdvertisement> convertToElectronicAds(List<GenericAdvertisement> ads){
        List<ElectricityAdvertisement> electsAds = null;
        try{
            electsAds = ads.stream().map(ad -> (ElectricityAdvertisement) ad).toList();
        }
        catch (Exception e){
            logger.warning("Failed to convert list from generic ads to car ads. please check the data. first ad id was:"+ads.get(0).getId());
        }

        return electsAds;
    }
    /**
     * Method to auto generate and set id in the advertisement object.
     *
     * @param - the entity to set id in.
     */
    public static void generateAndSetId(GenericAdvertisement advertisement) {
        // Generate a new ID for the advertisement, use UUID to ensure uniqueness
        String id = createId();
        advertisement.setId(id);
    }

    public static String createId(){
        return UUID.randomUUID().toString();
    }

    public static GenericAdvertisement findById(String id, ConcurrentLinkedQueue<GenericAdvertisement> allAdvertisements){
        GenericAdvertisement foundAd = allAdvertisements.stream()
                .filter(ad -> ad.getId().equals(id))
                .findFirst()
                .orElse(null);
        // May be null. return the result.
        if(foundAd == null){
            logger.info("ID:"+id+" in method 'findById' was not found. returning null.");
        }
        return foundAd;
    }
}
