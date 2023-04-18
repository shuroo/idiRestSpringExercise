package com.directinsuranceexercise.rest.controller;

import com.directinsuranceexercise.rest.model.AssetAdvertisement;
import com.directinsuranceexercise.rest.model.CRUDAdvertisementInterface;
import com.directinsuranceexercise.rest.utilities.AdvertisementUtils;
import com.directinsuranceexercise.rest.utilities.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**

 This class handles HTTP requests related to asset advertisements. It inherits from the abstract AdvertisementController
 and implements the CRUDAdvertisementInterface to perform basic CRUD operations on asset advertisements. It defines methods
 to create, update, retrieve and delete asset advertisements.
 @author shirirave
 @since 04-18-2023
 */
@RestController
@RequestMapping(value = "/assetAdvertisements", produces = "application/json")
public class AssetAdvertisementController extends AdvertisementController implements CRUDAdvertisementInterface {

    /**
     * HTTP POST method to create a new asset advertisement.
     *
     * @param assetAdvertisement the new asset advertisement to be created
     * @return a ResponseEntity containing the newly created asset advertisement or an error message if the operation failed
     * @throws Exception if the new advertisement's ID already exists in the system
     */
    @RequestMapping(value = "/create",
            method = RequestMethod.POST)
    public ResponseEntity<AssetAdvertisement> createAssetAdvertisement(@RequestBody AssetAdvertisement assetAdvertisement) throws Exception {
        return createAdvertisement(Constants.assetCategory, assetAdvertisement);
    }

    /**
     * HTTP PUT method to update an existing asset advertisement.
     *
     * @param id the ID of the asset advertisement to be updated
     * @param assetAdvertisement the updated asset advertisement
     * @return a ResponseEntity containing the updated asset advertisement or an error message if the operation failed
     * @throws Exception if the ID of the advertisement to be updated does not exist in the system
     */
    @PutMapping("/{id}")
    public ResponseEntity<AssetAdvertisement> updateAdvertisement(@PathVariable("id") String id,
                                                                  @RequestBody AssetAdvertisement assetAdvertisement)
            throws Exception {

        ResponseEntity response = super.preUpdateAdvertisement(id, assetAdvertisement);
        AssetAdvertisement existingAssetAdvertisement = null;
        try {
            existingAssetAdvertisement = (AssetAdvertisement) response.getBody();
        } catch (Exception e) {
            throw new Exception(AdvertisementUtils.createErrorEditMsg(assetAdvertisement.getCategory()));
        }
        if (existingAssetAdvertisement == null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        AdvertisementUtils.setAssetAd(existingAssetAdvertisement, assetAdvertisement);
        allAdvertisements.add(existingAssetAdvertisement);
        return ResponseEntity.ok(existingAssetAdvertisement);
    }

    /**
     * HTTP GET method to bring an asset advertisement to the top of the list.
     *
     * @param id the ID of the asset advertisement to be brought to the top
     * @return true if the operation was successful, false otherwise
     */
    @GetMapping("/{id}")
    @Override
    public boolean bringAdvertisementToTop(@PathVariable("id") String id) {
        return super.bringAdvertisementToTop(id);
    }


    /**
     * HTTP DELETE method to delete an existing asset advertisement.
     *
     * @param id the ID of the asset advertisement to be deleted
     * @return true if the operation was successful, false otherwise
     */
    @DeleteMapping("/{id}")
    @Override
    public boolean deleteAdvertisement(@PathVariable("id") String id) {
        return super.deleteAdvertisement(id);
    }
}
