package com.directinsuranceexercise.rest.view;

import com.directinsuranceexercise.rest.controller.AdvertisementController;
import com.directinsuranceexercise.rest.model.*;
import com.directinsuranceexercise.rest.utilities.AdvertisementUtils;
import com.directinsuranceexercise.rest.utilities.Constants;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;


@Route(value = "advertisement") // , layout = MainLayout.class
public class AdvertisementView extends VerticalLayout {
    private final RestTemplate restTemplate;

    private final String[] categoryOptions = { "All", Constants.assetCategory, Constants.carCategory, Constants.electricityCategory };

    private ComboBox<String> categoryDropdown;
    private Button filterButton;

    private List<GenericAdvertisement> allAds = AdManager.getInstance().getAdvertisements().stream().toList();


    private Grid<GenericAdvertisement> grid;

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

    private void filterByCategory() {
        String category = categoryDropdown.getValue();
        if (category.equals("All")) {
            grid.setItems(allAds);
        } else {
            List<GenericAdvertisement> filteredAds = AdvertisementUtils.filterByCategory(category, allAds);
            grid.setItems(filteredAds);
//            if (category.equals(Constants.assetCategory)) {
//                remove();
//                add(buildAssetGrid());
//            } else if (category.equals(Constants.carCategory)) {
//                remove(grid);
//                add(buildCarGrid());
//            } else if (category.equals(Constants.electricityCategory)) {
//                remove(grid);
//                add(buildElectronicsGrid());
          }
        }



    private Grid buildAssetGrid(){
        List<GenericAdvertisement> assetAds = AdvertisementUtils.filterByCategory(Constants.assetCategory,allAds);
        Grid<AssetAdvertisement> grid = new Grid<>();
        grid.setItems(AdvertisementUtils.convertToAssetAds(assetAds));
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
        List<GenericAdvertisement> carAds = AdvertisementUtils.filterByCategory(Constants.carCategory,allAds);
        Grid<CarAdvertisement> grid = new Grid<>();
        grid.setItems(AdvertisementUtils.convertToCarAds(carAds));
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
        List<GenericAdvertisement> electricityAdvertisements = AdvertisementUtils.filterByCategory(Constants.electricityCategory,allAds);
        Grid<ElectricityAdvertisement> grid = new Grid<>();
        grid.setItems(AdvertisementUtils.convertToElectronicAds(electricityAdvertisements));
        grid.addColumn(ElectricityAdvertisement::getId).setHeader("ID");
        grid.addColumn(ElectricityAdvertisement::getElectricityType).setHeader("Type");
        grid.addColumn(ElectricityAdvertisement::getPrice).setHeader("Price");
        grid.addColumn(ElectricityAdvertisement::getCondition).setHeader("Condition");
        grid.addColumn(ElectricityAdvertisement::getContactName).setHeader("ContactName");
        grid.addColumn(ElectricityAdvertisement::getContactPhoneNumber).setHeader("ContactPhone");
        return grid;
    }
    public AdvertisementView(RestTemplate restTemplate) {
         this.restTemplate = restTemplate;
        //Tab tab0 = new Tab("Add or Edit Advertisement");
//        Tab tab1 = new Tab("All Advertisements");
//        tab1.add(buildGenericGrid());
//        Tab tab2 = new Tab("Asset Advertisements");
//        tab2.add(buildAssetGrid());
//        Tab tab3 = new Tab("Car Advertisements");
//        tab3.add(buildCarGrid());
//        Tab tab4 = new Tab("Electronic Advertisements");
//        tab4.add(buildElectronicsGrid());
//        HorizontalLayout hl = new HorizontalLayout();
//        hl.add(tab1,tab2,tab3,tab4);
       // add(buildAssetGrid());

        // Initialize dropdown list and filter button
        categoryDropdown = new ComboBox<>("Category", categoryOptions);
        categoryDropdown.setValue("All");
        filterButton = new Button("Filter!", e -> filterByCategory());

        // Create the grid and add to the view
        grid = buildGenericGrid();
        add(new HorizontalLayout(categoryDropdown, new VerticalLayout(new Label("Filter by Category"),filterButton)), grid);

//        Grid grid = new Grid<>(GenericAdvertisement.class);
//        grid.setColumns("id", "category", "price", "contactName", "contactPhoneNumber");
//        grid.setItems(allAds);
//        add(grid);
    }
}

