package com.directinsuranceexercise.rest.utilities;

import com.directinsuranceexercise.rest.model.AssetAdvertisement;
import com.directinsuranceexercise.rest.model.CarAdvertisement;
import com.directinsuranceexercise.rest.model.ElectricityAdvertisement;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import com.directinsuranceexercise.rest.view.*;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;
import java.util.Optional;


public class ViewsUtils {

    private static void navigateTo(Class someClass) {
        UI.getCurrent().navigate(someClass);
    }

    /**
     * Method to add a generic top menu bar to a layout
     * @return MenuBar
     */
    public static MenuBar buildTopMenu(){

                MenuBar  menuBar = new MenuBar();
                menuBar.addItem("CRUD Ads", e -> navigateTo(MainView.class));
                menuBar.addItem("Filter By Category", e -> navigateTo(FilterByCategoryView.class));
                menuBar.addItem("Filter By Max Price", e -> navigateTo(FilterByMaxPriceView.class));
                menuBar.addItem("Show Asset Ads", e -> navigateTo(ShowAssetsView.class));
                menuBar.addItem("Show Car Ads", e -> navigateTo(ShowCarsView.class));
                menuBar.addItem("Show Electronic Ads", e -> navigateTo(ShowElectronicsView.class));

                return menuBar;
            }

    public static Grid<GenericAdvertisement> buildGenericGrid(List<GenericAdvertisement> allAds) {
        Grid<GenericAdvertisement> grid = new Grid<>();
        grid.setItems(allAds);
        grid.addColumn(GenericAdvertisement::getId).setHeader("ID").setFlexGrow(1).setWidth("30%");
        grid.addColumn(GenericAdvertisement::getCategory).setHeader("Category");
        grid.addColumn(GenericAdvertisement::getPrice).setHeader("Price");
        grid.addColumn(GenericAdvertisement::getContactName).setHeader("ContactName");
        grid.addColumn(GenericAdvertisement::getContactPhoneNumber).setHeader("ContactPhone");
        return grid;
    }

    /**
     * Method for creating a top label for each page
     * @param lblText
     * @return H2
     */
    public static H2 addTopLabel(String lblText){
        return new H2(lblText);
    }

    public static Grid buildAssetGrid(List<GenericAdvertisement> allAds) {
        List<GenericAdvertisement> assetAds = AdvertisementUtils.filterByCategory(Constants.assetCategory,allAds);
        Grid<AssetAdvertisement> grid = new Grid<>();
        grid.setItems(AdvertisementUtils.convertToAssetAds(assetAds));
        grid.addColumn(AssetAdvertisement::getId).setHeader("ID").setFlexGrow(1).setWidth("30%");
        grid.addColumn(AssetAdvertisement::getAssetAdType).setHeader("Type");
        grid.addColumn(AssetAdvertisement::getAssetSize).setHeader("Size");
        grid.addColumn(AssetAdvertisement::getNumberOfRooms).setHeader("NumberOfRooms");
        return grid;
    }

    public static Grid buildCarGrid(List<GenericAdvertisement> allAds) {
        List<GenericAdvertisement> carAds = AdvertisementUtils.filterByCategory(Constants.carCategory,allAds);
        Grid<CarAdvertisement> grid = new Grid<>();
        grid.setItems(AdvertisementUtils.convertToCarAds(carAds));
        grid.addColumn(CarAdvertisement::getId).setHeader("ID").setFlexGrow(1).setWidth("30%");
        grid.addColumn(CarAdvertisement::getManufacturer).setHeader("Manifecturer");
        grid.addColumn(CarAdvertisement::getPrice).setHeader("Price");
        grid.addColumn(CarAdvertisement::getModel).setHeader("Model");
        grid.addColumn(CarAdvertisement::getYear).setHeader("Year");
        grid.addColumn(CarAdvertisement::getColor).setHeader("Color");
        grid.addColumn(CarAdvertisement::getContactName).setHeader("ContactName");
        grid.addColumn(CarAdvertisement::getContactPhoneNumber).setHeader("ContactPhone");
        return grid;
    }

    public static Grid buildElectronicsGrid(List<GenericAdvertisement> allAds) {
        List<GenericAdvertisement> electricityAdvertisements = AdvertisementUtils.filterByCategory(Constants.electricityCategory,allAds);
        Grid<ElectricityAdvertisement> grid = new Grid<>();
        grid.setItems(AdvertisementUtils.convertToElectronicAds(electricityAdvertisements));
        grid.addColumn(ElectricityAdvertisement::getId).setHeader("ID").setFlexGrow(1).setWidth("30%");
        grid.addColumn(ElectricityAdvertisement::getElectricityType).setHeader("Type");
        grid.addColumn(ElectricityAdvertisement::getPrice).setHeader("Price");
        grid.addColumn(ElectricityAdvertisement::getCondition).setHeader("Condition");
        grid.addColumn(ElectricityAdvertisement::getContactName).setHeader("ContactName");
        grid.addColumn(ElectricityAdvertisement::getContactPhoneNumber).setHeader("ContactPhone");
        return grid;
    }

    public static HorizontalLayout createFilterByComponent(String currentPageLbl,
                                                    Button  filterButton,
                                                   Component criteriaField){

        VerticalLayout filterWrapper = new VerticalLayout(new Label(currentPageLbl), filterButton);
        HorizontalLayout layout = new HorizontalLayout(criteriaField,filterWrapper);
        return layout;
    }

    public static ComboBox<String> getCategoryOptions(){
        String[] categoryOptions = { Constants.genericCategory, Constants.assetCategory, Constants.carCategory, Constants.electricityCategory };

        ComboBox<String> categoryDropdown;
        // Initialize dropdown list and filter button
        categoryDropdown = new ComboBox<>("Category", categoryOptions);
        categoryDropdown.setValue(Constants.genericCategory);

        return categoryDropdown;
    }

    public static void setBorder(Component component){
        component.getStyle().set("border","5 px solid black");
    }
}
