//package com.directinsuranceexercise.rest.view;
//
//import com.directinsuranceexercise.rest.controller.AdvertisementController;
//import com.directinsuranceexercise.rest.utilities.Constants;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.dialog.Dialog;
//import com.vaadin.flow.component.formlayout.FormLayout;
//import com.vaadin.flow.component.html.Label;
//import com.vaadin.flow.component.notification.Notification;
//import com.vaadin.flow.component.select.Select;
//import com.vaadin.flow.component.textfield.NumberField;
//import com.vaadin.flow.component.textfield.TextArea;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.router.Route;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.atmosphere.util.IOUtils.close;
//
//@Route("basic")
//    public class BasicFormView  extends FormLayout {
//
//    @Autowired
//    AdvertisementController advertisementController = new AdvertisementController();
//
//    private Select<String>  getCategories(){
//        List<String> options = Arrays.asList(Constants.assetCategory, Constants.carCategory, Constants.electricityCategory);
//
//        // Create the select field and set the options
//        Select<String> select = new Select<>();
//        select.setItems(options);
//        return select;
//    }
//
//    private Select<String>  getActions(){
//        List<String> options = Arrays.asList(Constants.createOperation, Constants.editOperation, Constants.deleteOperation, Constants.bringToTopOperation);
//
//        // Create the select field and set the options
//        Select<String> select = new Select<>();
//        select.setItems(options);
//        return select;
//    }
//        public BasicFormView() {
//            // Create the fields for the main form
////            TextField nameField = new TextField("Name");
////            TextField emailField = new TextField("Email");
//            Button saveButton = new Button("Save", event -> {
//                advertisementController.createAdvertisement();
//                close();
//            }
//            );
//
//            // Add the fields to the main form
//            add(saveButton);
//
//            // Create the fields for the popup form
////            NumberField priceField = new NumberField("Price");
////            NumberField contactPhone = new NumberField("ContactPhone");
////            TextField contactNameField = new TextField("ContactName");
//            List<String> options = Arrays.asList(Constants.assetCategory, Constants.carCategory, Constants.electricityCategory);
//
//            // Create the select field and set the options
//            Select<String> select = new Select<>();
//            select.setItems(options);
//            select.onEnabledStateChanged(true);
//            TextArea contentField = new TextArea("Advertisement Content As Json");
//
//            TextField adId = new TextField("Ad ID");
//            Button savePopupButton = new Button("Save", event -> {
//                close();
//            });
//
//            // Create the popup dialog
//            Dialog popup = new Dialog();
//            popup.add(new Label("Create a new Ad!"));
//            popup.add(savePopupButton);
//
//            // Create the button to open the popup
//            Button popupButton = new Button("Create An Ad!", event -> {
//                popup.open();
//            });
//
//            // Add the button to the main form
//            add(popupButton);
//        }
//
//}
