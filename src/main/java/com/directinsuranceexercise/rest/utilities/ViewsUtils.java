package com.directinsuranceexercise.rest.utilities;

import com.directinsuranceexercise.rest.model.AssetAdvertisement;
import com.directinsuranceexercise.rest.model.CarAdvertisement;
import com.directinsuranceexercise.rest.model.ElectricityAdvertisement;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import com.directinsuranceexercise.rest.view.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


public class ViewsUtils {

    private static void navigateTo(Class someClass) {
        UI.getCurrent().navigate(someClass);
    }

    /**
     * Method to add a generic top menu bar to a layout
     *
     * @return MenuBar
     */

    public static MenuBar buildTopMenu() {

        MenuBar menuBar = new MenuBar();
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
        setHeaderCapitalizeLabel(resizeIdCol(grid.addColumn(GenericAdvertisement::getId)),Constants.id);
        setHeaderCapitalizeLabel(grid.addColumn(GenericAdvertisement::getCategory),Constants.category);
        setHeaderCapitalizeLabel(grid.addColumn(GenericAdvertisement::getPrice),Constants.price);
        setHeaderCapitalizeLabel(grid.addColumn(GenericAdvertisement::getContactName),Constants.contactName);
        setHeaderCapitalizeLabel(grid.addColumn(GenericAdvertisement::getContactPhoneNumber),Constants.contactPhoneNumber);
        return grid;
    }

    /**
     * Method for formatting the first letter of a string as capital letter ( for table headers etc )
     * @param str -
     * @return
     */
    public static String capitalize(String str){
        return StringUtils.capitalize(str);
    }

    private static Grid.Column resizeIdCol(Grid.Column col){
        col.setFlexGrow(1).setWidth("30%");
        return col;
    }

    private static Grid.Column setHeaderCapitalizeLabel(Grid.Column col,String headerLbl){
        col.setHeader(capitalize(headerLbl));
        return col;
    }

    /**
     * Method for creating a top label for each page
     *
     * @param lblText
     * @return H2
     */
    public static H2 addTopLabel(String lblText) {
        return new H2(capitalize(lblText));
    }

    public static Grid buildAssetGrid(List<GenericAdvertisement> allAds) {
        List<GenericAdvertisement> assetAds = AdvertisementUtils.filterByCategory(Constants.assetCategory, allAds);
        Grid<AssetAdvertisement> grid = new Grid<>();
        grid.setItems(AdvertisementUtils.convertToAssetAds(assetAds));
        setHeaderCapitalizeLabel(resizeIdCol(grid.addColumn(AssetAdvertisement::getId)),Constants.id);
        setHeaderCapitalizeLabel(grid.addColumn(AssetAdvertisement::getAssetAdType),Constants.assetAdType);
        setHeaderCapitalizeLabel(grid.addColumn(AssetAdvertisement::getAssetSize),Constants.assetSize);
        setHeaderCapitalizeLabel(grid.addColumn(AssetAdvertisement::getNumberOfRooms),Constants.numberOfRooms);
        return grid;
    }

    public static Grid buildCarGrid(List<GenericAdvertisement> allAds) {
        List<GenericAdvertisement> carAds = AdvertisementUtils.filterByCategory(Constants.carCategory, allAds);
        Grid<CarAdvertisement> grid = new Grid<>();
        grid.setItems(AdvertisementUtils.convertToCarAds(carAds));
        setHeaderCapitalizeLabel(resizeIdCol(grid.addColumn(CarAdvertisement::getId)),Constants.id);
        setHeaderCapitalizeLabel(grid.addColumn(CarAdvertisement::getManufacturer),Constants.manufacturer);
        setHeaderCapitalizeLabel(grid.addColumn(CarAdvertisement::getPrice),Constants.price);
        setHeaderCapitalizeLabel(grid.addColumn(CarAdvertisement::getModel),Constants.model);
        setHeaderCapitalizeLabel(grid.addColumn(CarAdvertisement::getYear),Constants.year);
        setHeaderCapitalizeLabel(grid.addColumn(CarAdvertisement::getColor),Constants.color);
        setHeaderCapitalizeLabel(grid.addColumn(CarAdvertisement::getContactName),Constants.contactName);
        setHeaderCapitalizeLabel(grid.addColumn(CarAdvertisement::getContactPhoneNumber),Constants.contactPhoneNumber);
        return grid;
    }

    public static Grid buildElectronicsGrid(List<GenericAdvertisement> allAds) {
        List<GenericAdvertisement> electricityAdvertisements = AdvertisementUtils.filterByCategory(Constants.electricityCategory, allAds);
        Grid<ElectricityAdvertisement> grid = new Grid<>();
        grid.setItems(AdvertisementUtils.convertToElectronicAds(electricityAdvertisements));
        setHeaderCapitalizeLabel(resizeIdCol(grid.addColumn(ElectricityAdvertisement::getId)),Constants.id);
        setHeaderCapitalizeLabel(grid.addColumn(ElectricityAdvertisement::getElectricityType),Constants.electricityType);
        setHeaderCapitalizeLabel(grid.addColumn(ElectricityAdvertisement::getPrice),Constants.price);
        setHeaderCapitalizeLabel(grid.addColumn(ElectricityAdvertisement::getCondition),Constants.condition);
        setHeaderCapitalizeLabel(grid.addColumn(ElectricityAdvertisement::getContactName),Constants.contactName);
        setHeaderCapitalizeLabel(grid.addColumn(ElectricityAdvertisement::getContactPhoneNumber),Constants.contactPhoneNumber);
        return grid;
    }

    public static HorizontalLayout createFilterByComponent(Button filterButton,
                                                           Component criteriaField) {

        VerticalLayout filterWrapper = new VerticalLayout(new Label(Constants.emptyString),filterButton);
        HorizontalLayout layout = new HorizontalLayout(criteriaField, filterWrapper);
        return layout;
    }

    public static ComboBox<String> getCategoryOptions() {
        String[] categoryOptions = {Constants.genericCategory, Constants.assetCategory, Constants.carCategory, Constants.electricityCategory};

        ComboBox<String> categoryDropdown;
        // Initialize dropdown list and filter button
        categoryDropdown = new ComboBox<>(Constants.category, categoryOptions);
        categoryDropdown.setValue(Constants.genericCategory);

        return categoryDropdown;
    }

}
