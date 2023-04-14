package com.directinsuranceexercise.rest.utilities;

import com.directinsuranceexercise.rest.model.AssetAdvertisement;
import com.directinsuranceexercise.rest.model.CarAdvertisement;
import com.directinsuranceexercise.rest.model.ElectricityAdvertisement;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import com.directinsuranceexercise.rest.view.FilterByCategoryView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;
import java.util.Optional;

public class ViewsUtils {
    public static Grid<GenericAdvertisement> buildGenericGrid(List<GenericAdvertisement> allAds) {
        Grid<GenericAdvertisement> grid = new Grid<>();
        grid.setItems(allAds);
        grid.addColumn(GenericAdvertisement::getId).setHeader("ID");
        grid.addColumn(GenericAdvertisement::getCategory).setHeader("Category");
        grid.addColumn(GenericAdvertisement::getPrice).setHeader("Price");
        grid.addColumn(GenericAdvertisement::getContactName).setHeader("ContactName");
        grid.addColumn(GenericAdvertisement::getContactPhoneNumber).setHeader("ContactPhone");
        return grid;
    }


    private Grid buildAssetGrid(List<GenericAdvertisement> allAds) {
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

    private Grid buildCarGrid(List<GenericAdvertisement> allAds) {
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

    private Grid buildElectronicsGrid(List<GenericAdvertisement> allAds) {
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

    public static HorizontalLayout createFilterByComponent(String currentPageLbl,
                                                    Button nxtPageBtn,
                                                    Button  filterButton,
                                                    Optional<TextField> maybeMaxPriceField){

        nxtPageBtn.getStyle().set("background-color", "blue");
        nxtPageBtn.getStyle().set("color", "black");
        VerticalLayout filterWrapper = new VerticalLayout(new Label(currentPageLbl), filterButton,nxtPageBtn);
        Component maxPriceField = maybeMaxPriceField.orElse(null);
        return new HorizontalLayout( maxPriceField,filterWrapper);
    }

    public static void setBorder(Component component){
        component.getStyle().set("border","5 px solid black");
    }
}
