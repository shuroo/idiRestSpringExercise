package com.directinsuranceexercise.rest.view;

import com.directinsuranceexercise.rest.model.*;
import com.directinsuranceexercise.rest.utilities.AdvertisementUtils;
import com.directinsuranceexercise.rest.utilities.Constants;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;


@Route(value = "advertisement") // , layout = MainLayout.class
public class AdvertisementView extends VerticalLayout {
    private final RestTemplate restTemplate;

    private ConcurrentLinkedQueue allAds = AdManager.getInstance().getAdvertisements();

    private Grid buildGenericGrid(){
        Grid<GenericAdvertisement> grid = new Grid<>();
        grid.setItems(allAds);
        grid.addColumn(GenericAdvertisement::getId).setHeader("ID");
        grid.addColumn(GenericAdvertisement::getCategory).setHeader("Category");
        grid.addColumn(GenericAdvertisement::getPrice).setHeader("Price");
        grid.addColumn(GenericAdvertisement::getContactName).setHeader("ContactName");
        grid.addColumn(GenericAdvertisement::getContactPhoneNumber).setHeader("ContactPhone");
        return grid;
    }


    private Grid buildAssetGrid(){
        List<AssetAdvertisement> assetAds = AdvertisementUtils.filterByCategory(Constants.assetCategory,allAds);
        Grid<AssetAdvertisement> grid = new Grid<>();
        grid.setItems(assetAds);
        grid.addColumn(AssetAdvertisement::getId).setHeader("ID");
        grid.addColumn(AssetAdvertisement::getAssetAdType).setHeader("Type");
        grid.addColumn(AssetAdvertisement::getPrice).setHeader("Price");
        grid.addColumn(AssetAdvertisement::getAssetSize).setHeader("Size");
        grid.addColumn(AssetAdvertisement::getNumberOfRooms).setHeader("NumberOfRooms");
        grid.addColumn(AssetAdvertisement::getContactName).setHeader("ContactName");
        grid.addColumn(AssetAdvertisement::getContactPhoneNumber).setHeader("ContactPhone");
        return grid;
    }

    private Grid buildCarGrid(){
        List<CarAdvertisement> carAds = AdvertisementUtils.filterByCategory(Constants.carCategory,allAds);
        Grid<CarAdvertisement> grid = new Grid<>();
        grid.setItems(carAds);
        grid.addColumn(CarAdvertisement::getId).setHeader("ID");
        grid.addColumn(CarAdvertisement::getManufacturer).setHeader("Manifecturer");
        grid.addColumn(CarAdvertisement::getPrice).setHeader("Price");
        grid.addColumn(CarAdvertisement::getModel).setHeader("Model");
        grid.addColumn(CarAdvertisement::getYear).setHeader("Year");
        grid.addColumn(CarAdvertisement::getColor).setHeader("Color");
        grid.addColumn(CarAdvertisement::getContactName).setHeader("ContactName");
        grid.addColumn(CarAdvertisement::getContactPhoneNumber).setHeader("ContactPhone");
        return grid;
    }

    private Grid buildElectronicsGrid(){
        List<ElectricityAdvertisement> electricityAdvertisements = AdvertisementUtils.filterByCategory(Constants.electricityCategory,allAds);
        Grid<ElectricityAdvertisement> grid = new Grid<>();
        grid.setItems(electricityAdvertisements);
        grid.addColumn(ElectricityAdvertisement::getId).setHeader("ID");
        grid.addColumn(ElectricityAdvertisement::getElectricityType).setHeader("Type");
        grid.addColumn(ElectricityAdvertisement::getPrice).setHeader("Price");
        grid.addColumn(ElectricityAdvertisement::getCondition).setHeader("Condition");
        grid.addColumn(ElectricityAdvertisement::getContactName).setHeader("ContactName");
        grid.addColumn(ElectricityAdvertisement::getContactPhoneNumber).setHeader("ContactPhone");
        return grid;
    }
//    private Grid<GenericAdvertisement> filterByCategory(String category) throws Exception {
//        List<GenericAdvertisement> ads = AdvertisementUtils.filterByCategory(category, AdManager.getInstance().getAdvertisements());
//        if (category.equals(Constants.assetCategory)) {
//            return buildAssetGrid(AdvertisementUtils.convertToAssetAds(ads));
//        } else if (category.equals(Constants.carCategory)) {
//            return buildCarGrid(AdvertisementUtils.convertToCarAds(ads));
//        } else if (category.equals(Constants.electricityCategory)) {
//            return buildElectronicsGrid(AdvertisementUtils.convertToElectronicAds(ads));
//        }
//        throw new Exception("Invalid or unknown category detected. aborting.");
//    }
    public AdvertisementView(RestTemplate restTemplate) {
         this.restTemplate = restTemplate;
        Tab tab0 = new Tab("Add or Edit Advertisement");
        Tab tab1 = new Tab("All Advertisements");
        tab1.add(buildGenericGrid());
        Tab tab2 = new Tab("Asset Advertisements");
        tab2.add(buildAssetGrid());
        Tab tab3 = new Tab("Car Advertisements");
        tab3.add(buildCarGrid());
        Tab tab4 = new Tab("Electronic Advertisements");
        tab4.add(buildElectronicsGrid());
        add(tab0,tab1,tab2,tab3,tab4);
    }
}

