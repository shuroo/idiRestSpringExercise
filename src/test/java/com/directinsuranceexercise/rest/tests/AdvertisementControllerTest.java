//package com.directinsuranceexercise.rest.tests;
//
//import com.directinsuranceexercise.rest.controller.AdvertisementController;
//import com.directinsuranceexercise.rest.model.AdManager;
//import com.directinsuranceexercise.rest.model.GenericAdvertisement;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.List;
//
//class AdvertisementControllerTest {
//
//    private AdvertisementController controller;
//
//    @BeforeEach
//    void setUp() {
//        controller = new AdvertisementController();
//        AdManager adManager = AdManager.getInstance();
//        //adManager.clearAdvertisementsList();
//        //adManager.createAdvertisementsFromConfigFile();
//    }
//
//    @Test
//    void testGetAll() {
//        ResponseEntity<List<GenericAdvertisement>> response = controller.getAll();
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Assertions.assertEquals(10, response.getBody().size());
//    }
//
//    @Test
//    void testGetByCategory() {
//        ResponseEntity<List<GenericAdvertisement>> response = controller.getByCategory("Automobiles");
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Assertions.assertEquals(2, response.getBody().size());
//    }
//
//    @Test
//    void testGetByMaxPrice() {
//        ResponseEntity<List<GenericAdvertisement>> response = controller.getByMaxPrice(40000);
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Assertions.assertEquals(6, response.getBody().size());
//    }
//
//    @Test
//    void testJumpAssetAdvertisement() {
//        ResponseEntity<List<GenericAdvertisement>> response1 = controller.jumpAssetAdvertisement(4L);
//        Assertions.assertEquals(HttpStatus.OK, response1.getStatusCode());
//        Assertions.assertEquals(10, response1.getBody().size());
//        Assertions.assertEquals(4L, response1.getBody().get(0).getId());
//
//        ResponseEntity<List<GenericAdvertisement>> response2 = controller.jumpAssetAdvertisement(8L);
//        Assertions.assertEquals(HttpStatus.OK, response2.getStatusCode());
//        Assertions.assertEquals(10, response2.getBody().size());
//        Assertions.assertEquals(8L, response2.getBody().get(0).getId());
//    }
//
//    @Test
//    void testGetAssetAdvertisement() {
//        ResponseEntity<GenericAdvertisement> response = controller.getAssetAdvertisement(3L);
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Assertions.assertEquals(3L, response.getBody().getId());
//        Assertions.assertEquals("Real Estate", response.getBody().getCategory());
//    }
//
//    @Test
//    void testPreCreateAd() throws Exception {
//        GenericAdvertisement newAd = new GenericAdvertisement();
//        newAd.setCategory("Toys");
//        newAd.setContactName("Jane Smith");
//        newAd.setContactPhoneNumber("555-1234");
//        newAd.setPrice(17);
//
//        GenericAdvertisement result = controller.preCreateAd(newAd);
//        Assertions.assertEquals(11L, result.getId());
//        Assertions.assertEquals("Toys", result.getCategory());
//        Assertions.assertEquals("Jane Smith", result.getContactName());
//        Assertions.assertEquals("555-1234", result.getContactPhoneNumber());
//        Assertions.assertEquals(15.99, result.getPrice());
//    }
//
//    @Test
//    void testDeleteAdvertisement() {
//        boolean result = controller.deleteAdvertisement(5L);
//        Assertions.assertTrue(result);
//        ResponseEntity<List<GenericAdvertisement>> response = controller.getAll();
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Assertions.assertEquals(9, response.getBody().size());
//    }
//
////    @Test
////    void testUpdateAdvertisement() throws Exception {
////        GenericAdvertisement updateAd = new GenericAdvertisement();
////        updateAd.setId(6L);
////        updateAd.setCategory("Real Estate");
////        updateAd.setContactName("John Doe");
////        updateAd.setContactPhoneNumber("555-5678");
////        updateAd.setPrice(120000.0);
////
////        ResponseEntity<GenericAdvertisement> response = controller.updateAdvertisement(
//}