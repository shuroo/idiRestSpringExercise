//package com.directinsuranceexercise.rest.controller;
//
//import com.directinsuranceexercise.rest.config.AdvertisementConfig;
//import com.directinsuranceexercise.rest.model.AdManager;
//import jakarta.annotation.PostConstruct;
//import jakarta.annotation.PreDestroy;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
//import org.springframework.context.annotation.*;
//import java.util.List;
//
//@Configuration
//public class PostProcessorSingletonBean {
//
//    AdManager adManager = AdManager.getInstance();
//    private static boolean initialized = false;
//    @Autowired
//    private AdvertisementConfig config;
//
//    @Bean
//    public static CommonAnnotationBeanPostProcessor commonAnnotationBeanPostProcessorAsset() {
//        // singleton pattern:
//        return new CommonAnnotationBeanPostProcessor();
//    }
//
//    @PostConstruct
//    @Bean(initMethod = "init", destroyMethod = "destroy")
//    @DependsOn("commonAnnotationBeanPostProcessorAsset")
//    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
//    public MyBean myBean() {
//        return new MyBean();
//    }
//
//    private class MyBean {
//        @PostConstruct
//        public void init() {
//            if (!initialized) {
//                System.out.println("=====1====");
//                List allAdvertisements = adManager.getAdvertisementsList();
//                allAdvertisements.add(config.getSampleAssetAdvertisement());
//                allAdvertisements.add(config.getSampleCarAdvertisement());
//                allAdvertisements.add(config.getSampleElectronicsAdvertisement());
//                initialized = true;
//            }
//        }
//
//        @PreDestroy
//        public void destroy() {
//            // Cleanup code here
//        }
//    }
//}
//
