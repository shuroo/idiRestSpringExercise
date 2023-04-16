//package com.directinsuranceexercise.vaadin;
//
//import com.directinsuranceexercise.rest.model.AssetAdvertisement;
//import com.directinsuranceexercise.rest.utilities.Constants;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.formlayout.FormLayout;
//import com.vaadin.flow.component.grid.Grid;
//import com.vaadin.flow.component.html.H1;
//import com.vaadin.flow.component.html.H2;
//import com.vaadin.flow.component.notification.Notification;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.textfield.IntegerField;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.data.binder.BeanValidationBinder;
//import com.vaadin.flow.data.binder.Binder;
//import com.vaadin.flow.data.binder.ValidationException;
//import com.vaadin.flow.data.provider.DataProvider;
//import com.vaadin.flow.data.provider.ListDataProvider;
//import com.vaadin.flow.router.Route;
//import org.springframework.beans.factory.annotation.Autowired;
//
//@Route("assetAdvertisements")
//public class AssetAdvertisementView extends VerticalLayout {
//
//    private Grid<AssetAdvertisement> grid = new Grid<>(AssetAdvertisement.class);
//    private TextField assetName = new TextField("Asset Name");
//    private TextField assetDescription = new TextField("Asset Description");
//    private IntegerField assetSize = new IntegerField("Asset Size");
//    private TextField assetAdType = new TextField("Asset Ad Type");
//    private IntegerField numberOfRooms = new IntegerField("Number of Rooms");
//
//    private Button create = new Button("Create");
//    private Button update = new Button("Update");
//    private Button delete = new Button("Delete");
//
//    private Binder<AssetAdvertisement> binder = new BeanValidationBinder<>(AssetAdvertisement.class);
//    private AssetAdvertisement assetAdvertisement = new AssetAdvertisement();
//
//    private ListDataProvider<AssetAdvertisement> dataProvider;
//
//    @Autowired
//    private AssetAdvertisementClient assetAdvertisementClient;
//
//    public AssetAdvertisementView() {
//        setSizeFull();
//        HorizontalLayout horizontalLayout = new HorizontalLayout();
//        horizontalLayout.add(assetName, assetDescription, assetSize, assetAdType, numberOfRooms);
//        add(new H1("Asset Advertisements"), createHeader(), horizontalLayout, createGrid(), createFormLayout());
//        binder.bindInstanceFields(this);
//
//        create.addClickListener(e -> {
//            try {
//                binder.writeBean(assetAdvertisement);
//             //   assetAdvertisement.setAssetCategory(Constants.assetCategory);
//             //   assetAdvertisementClient.createAssetAdvertisement(assetAdvertisement);
//                dataProvider.getItems().add(assetAdvertisement);
//                dataProvider.refreshAll();
//                Notification.show("Asset Advertisement created successfully.");
//             //   clearFormFields();
//            } catch (ValidationException ex) {
//                Notification.show("Please check the input fields.");
//            }
//        });
//
//
//        private void clearFormFields() {
//            nameField.clear();
//            emailField.clear();
//        }
//
//        update.addClickListener(e -> {
//            try {
//                binder.writeBean(assetAdvertisement);
//                assetAdvertisementClient.updateAssetAdvertisement(assetAdvertisement.getId(), assetAdvertisement);
//                dataProvider.refreshAll();
//                Notification.show("Asset Advertisement updated successfully.");
//                clearFormFields();
//            } catch (ValidationException ex) {
//                Notification.show("Please check the input fields.");
//            }
//        });
//
//        delete.addClickListener(e -> {
//            // assetAdvertisementClient.deleteAssetAdvertisement(assetAdvertisement.getId());
//            dataProvider.getItems().remove(assetAdvertisement);
//            dataProvider.refreshAll();
//            Notification.show("Asset Advertisement deleted successfully.");
//            // clearFormFields();
//        });
//
//        grid.asSingleSelect().addValueChangeListener(event -> {
//            assetAdvertisement = event.getValue();
//            binder.setBean(assetAdvertisement);
//        });
//    }
//
//    private HorizontalLayout createHeader() {
//        H2 header = new H2("Create, Read, Update and Delete Asset Advertisements");
//        HorizontalLayout horizontalLayout = new HorizontalLayout(header);
//        horizontalLayout.setWidth("100%");
//        horizontalLayout.setAlignItems(Alignment.CENTER);
//        return horizontalLayout;
//    }
//}
