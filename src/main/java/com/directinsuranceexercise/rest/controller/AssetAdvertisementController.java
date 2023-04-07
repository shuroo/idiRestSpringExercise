package com.directinsuranceexercise.rest.controller;

import com.directinsuranceexercise.rest.model.AssetAdvertisement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/assetAdvertisements",  produces = "application/json")
public class AssetAdvertisementController {

//        @Autowired
//        private AssetAdvertisementRepository assetAdvertisementRepository;

    // Create an in-memory list to store the User objects
    private List<AssetAdvertisement> assetAdvertisementList = new ArrayList<>();

        private AssetAdvertisement findById(Long requestedId){
                if (requestedId != null) {

                    return assetAdvertisementList.stream()
                            .filter(ad -> ad.getId() == requestedId)
                            .findFirst()
                            .orElse(null);
                }
                return null;
        }

        @GetMapping("/all")
        public ResponseEntity<List<AssetAdvertisement>> getAll() {

            return ResponseEntity.ok(assetAdvertisementList);
        }
        @GetMapping("/jump/{id}")
        public ResponseEntity<List<AssetAdvertisement>> jumpAssetAdvertisement(@PathVariable("id") Long id) {

            // Find the user with the given ID and move it to the beginning of the list
            for (int i = 0; i < assetAdvertisementList.size(); i++) {
                AssetAdvertisement ad = assetAdvertisementList.get(i);
                if (ad.getId() == id) {
                    assetAdvertisementList.remove(i);
                    assetAdvertisementList.add(0, ad);
                    break;
                    }
                }
                return ResponseEntity.ok(assetAdvertisementList);
        }
        @GetMapping("/{id}")
        public ResponseEntity<AssetAdvertisement> getAssetAdvertisement(@PathVariable("id") Long id) {

            AssetAdvertisement ad =  assetAdvertisementList.stream()
                    .filter(user -> user.getId() == id)
                    .findFirst()
                    .orElse(null);
            return ResponseEntity.ok().body(ad);
           // return assetAdvertisementRepository.findById(id).orElse(null);
        }

        // @PostMapping("/create assetAdvertisements")
        @RequestMapping(value = "/create",
                method=RequestMethod.POST)
        public ResponseEntity<AssetAdvertisement> createAssetAdvertisement(@RequestBody AssetAdvertisement assetAdvertisement) {
            // Generate a new ID for the user and add it to the list
            Long id = (long) (assetAdvertisementList.size() + 1);
            assetAdvertisement.setId(id);
            assetAdvertisementList.add(assetAdvertisement);
            return ResponseEntity.ok().body(assetAdvertisement);
           // return assetAdvertisementRepository.save(AssetAdvertisement);
        }

        @PutMapping("/{id}")
        public ResponseEntity<AssetAdvertisement> updateAssetAdvertisement(@PathVariable("id") Long id,
                                                           @RequestBody AssetAdvertisement assetAdvertisement) {
           // AssetAdvertisement existingAssetAdvertisement = assetAdvertisementRepository.findById(id).orElse(null);

            //todo: this should be a method!
            AssetAdvertisement existingAssetAdvertisement = assetAdvertisementList.stream()
                    .filter(ad -> ad.getId() == id)
                    .findFirst()
                    .orElse(null);
            if (existingAssetAdvertisement == null) {
                // If the user doesn't exist, return null
                return null;
            }
            existingAssetAdvertisement.setAdType(assetAdvertisement.getAdType());
            existingAssetAdvertisement.setContactName(assetAdvertisement.getContactName());
            existingAssetAdvertisement.setContactPhoneNumber(assetAdvertisement.getContactPhoneNumber());
            existingAssetAdvertisement.setAssetSize(assetAdvertisement.getAssetSize());
            existingAssetAdvertisement.setPrice(assetAdvertisement.getPrice());
            existingAssetAdvertisement.setNumberOfRooms(assetAdvertisement.getNumberOfRooms());
            existingAssetAdvertisement.setId(assetAdvertisement.getId());
            assetAdvertisementList.add(existingAssetAdvertisement);
            return ResponseEntity.ok(existingAssetAdvertisement) ;
        }

        @DeleteMapping("/{id}")
        public boolean deleteAssetAdvertisement(@PathVariable("id") Long id) {
            return assetAdvertisementList.removeIf(user -> user.getId() == id );
            //assetAdvertisementRepository.deleteById(id);
        }
    }

