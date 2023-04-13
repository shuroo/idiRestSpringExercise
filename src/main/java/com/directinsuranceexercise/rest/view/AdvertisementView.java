package com.directinsuranceexercise.rest.view;

import com.directinsuranceexercise.rest.controller.AdvertisementController;
import com.directinsuranceexercise.rest.model.AdManager;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;


@Route(value = "advertisement") // , layout = MainLayout.class
public class AdvertisementView extends VerticalLayout {
    private final RestTemplate restTemplate;

    // todo: why is autowire not working??
    @Autowired
    AdvertisementController advertisementController = new AdvertisementController();

    private Grid<GenericAdvertisement> createGenericAdsGrid(){
        Grid<GenericAdvertisement> grid = new Grid<>();
        ConcurrentLinkedQueue ads = AdManager.getInstance().getAdvertisementsList();
        grid.setItems(ads);
      //  advertisementController.getByCategory("asset");
        grid.addColumn(GenericAdvertisement::getId).setHeader("ID");
        grid.addColumn(GenericAdvertisement::getCategory).setHeader("Category");
        grid.addColumn(GenericAdvertisement::getPrice).setHeader("Price");
        grid.addColumn(GenericAdvertisement::getContactName).setHeader("ContactName");
        grid.addColumn(GenericAdvertisement::getContactPhoneNumber).setHeader("ContactPhone");
        return grid;
    }
    public AdvertisementView(RestTemplate restTemplate) {
         this.restTemplate = restTemplate;
        add(createGenericAdsGrid());
//
//        Tab tab1 = new Tab("Tab 1");
//        Tab tab2 = new Tab("Tab 2");
//        Tab tab3 = new Tab("Tab 3");

    }
}

