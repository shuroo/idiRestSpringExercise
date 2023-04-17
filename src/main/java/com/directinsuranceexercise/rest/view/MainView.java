package com.directinsuranceexercise.rest.view;

import com.directinsuranceexercise.rest.model.AdManager;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import com.directinsuranceexercise.rest.utilities.Constants;
import com.directinsuranceexercise.rest.utilities.ViewsUtils;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Route(value = "/")
public class MainView extends VerticalLayout {

    private Grid<GenericAdvertisement> grid;

    private List<GenericAdvertisement> allAds = AdManager.getInstance().getAdvertisements().stream().toList();

    final String createOperation = "CREATE";
    final String updateOperation = "UPDATE";
    final String deleteOperation = "DELETE";
    final String jumpOperation = "JumpToTop";

    final String baseUrl = "http://localhost:8080/";

    final String pageLabelText = "CRUD Advertisements";
    private final TextArea jsonTextArea;
    private final Button createButton;
    private final Button updateButton;
    private final Button deleteButton;
    private final Button jumpToTop;

    private final ComboBox<String> categories = ViewsUtils.getCategoryOptions();
    private RestTemplate restTemplate = new RestTemplate();
    private final Binder<JsonBean> binder = new Binder<>(JsonBean.class);
    private final JsonBean bean = new JsonBean();

    private void setOnClickHandler(Grid<GenericAdvertisement> grid){
        grid.addItemClickListener(event -> {
            // get the selected row's data as a JsonObject
            GenericAdvertisement ad = event.getItem();
            JSONObject jsonAd = new JSONObject();
            try {
                jsonAd.put("id",ad.getId());
                jsonAd.put("price",ad.getPrice());
                jsonAd.put("contactName",ad.getContactName());
                jsonAd.put("contactPhoneNumber",ad.getContactPhoneNumber());

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            // set the JSON string as the text of the text area
            jsonTextArea.setValue(jsonAd.toString());
        });
    }

    private HttpMethod matchHttpMethod(String httpMethodString){

                switch(httpMethodString) {
                    case createOperation:
                        return HttpMethod.POST;
                    case updateOperation:
                        return HttpMethod.PUT;
                    case deleteOperation:
                        return HttpMethod.DELETE;
                    case jumpOperation:
                        return HttpMethod.GET;
                }

                return HttpMethod.POST;

    }
    private String buildUrlPrefixByCategoryAndHttpMethod(String httpMethod,JSONObject requestBody) throws JSONException{
        String urlPrefix = "";

        // todo: to support update we need something like: assetAdvertisements/{id}. can we switch all to generic operations?
        if(httpMethod.equals(jumpOperation) || httpMethod.equals(deleteOperation)){
            String id = requestBody.getString("id");
            return "assetAdvertisements/"+id;
        }
        String category = categories.getValue();
        switch (category) {
            case Constants.genericCategory:
                throw new JSONException("Invalid category, please select one and try again");
            case Constants.assetCategory:
                urlPrefix = "assetAdvertisements/";
                return urlPrefix;
            case Constants.carCategory:
                urlPrefix = "carAdvertisements/";
                return urlPrefix;
            case Constants.electricityCategory:
                urlPrefix = "electricityAdvertisements/";
                return urlPrefix;
        }

        return urlPrefix;
    }

    private boolean validateFullJsonStructure(JSONObject requestBody, String httpMethod) throws JSONException {
        requestBody.getDouble("price");
        requestBody.getString("contactPhoneNumber");
        requestBody.getString("contactName");
        String category = categories.getValue();
        switch (category) {
            case Constants.genericCategory:
                Notification.show("Please choose a category", 3000, Notification.Position.BOTTOM_CENTER);
                return false;
            case Constants.assetCategory:
                requestBody.getInt("assetSize");
                requestBody.getInt("numberOfRooms");
                return true;
            case Constants.carCategory:
                requestBody.getString("color");
                requestBody.getString("model");
                requestBody.getString("manifacturer");
                requestBody.getInt("year");
                requestBody.getInt("km");
                return true;
            case Constants.electricityCategory:
                requestBody.getString("electricityType");
                requestBody.getString("condition");
                return true;
        }

        return true;
    }

    /**
     * Make sure the request has all the needed fields
     * @param requestBody
     * @param httpMethod
     * @return
     * @throws JSONException
     */

    //todo: add all the below to the constants:
    private boolean validateRequest(JSONObject requestBody,String httpMethod) throws JSONException {
        // for any request except creation, we should verify the id field exists.
        if(!httpMethod.equals(createOperation)){
            requestBody.getString("id");
        }
        // For 'Create' or 'Edit', verify that we have a full json structure with all the Ad required fields
        if(httpMethod.equals(createOperation) || httpMethod.equals(createOperation)){
            return validateFullJsonStructure(requestBody,httpMethod);
        }

        return true;
    }
    /**
     * Perform CRUD operations as needed
     */
    private void performCRUD(Button operationBtn){

        validateJson();
        String jsonString = jsonTextArea.getValue();
        JSONTokener tokener = new JSONTokener(jsonString);
        JSONObject requestBody = null;

        try {
            requestBody  = new JSONObject(tokener);

            // todo: Change this to use a text field in case of delete or jump

            //todo: move to constants ... etc.

            // todo: one can shorten it if the 'create' was generic!

                // Send the request
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                String category = categories.getValue();
                requestBody.put("category",category);
                HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

                HttpMethod method = null;
                String urlSuffix = operationBtn.getText().toLowerCase();
                String urlPrefix = null;
                //todo: change requestBody param name

                String httpMethodString = operationBtn.getText();
                validateRequest(requestBody,httpMethodString);

//                //todo: seperate this to methods:
//                switch(httpMethodString){
//                    case createOperation:
//                        method = HttpMethod.POST;
//                    case updateOperation:
//                        method = HttpMethod.PUT;
//                        String id = requestBody.getString("id");
//                        urlSuffix = urlSuffix+"/"+id;
//                    case deleteOperation:
//                        method = HttpMethod.DELETE;
//                        urlPrefix = "/advertisements/"+id;
//                    case jumpOperation:
//                        method = HttpMethod.GET;
//                        urlPrefix = "advertisements/"+id; // <- todo: this is redundant
//                }

                urlPrefix = buildUrlPrefixByCategoryAndHttpMethod(httpMethodString,requestBody);

                method = matchHttpMethod( httpMethodString);
                String url = baseUrl+urlPrefix+"/"+urlSuffix;
                ResponseEntity<String> response = restTemplate.exchange(url, method, entity, String.class);

                System.out.println("*****"+url+","+method);
                // Reload the grid with the updated data
                allAds = AdManager.getInstance().getAdvertisements().stream().toList();
                grid.setItems(allAds);

                // Check the response status code
                if (response.getStatusCode() == HttpStatus.OK) {
                    Notification.show("Successfully sending http request:"+method+". Response body was:"+response.getBody(), 3000,
                            Notification.Position.BOTTOM_CENTER);
                } else {
                    Notification.show("Error sending http request:"+method+".Detected status:"+response.getStatusCode()+". aborting.", 3000,
                            Notification.Position.BOTTOM_CENTER);
                }



        } catch (JSONException e) {
            Notification.show("Error detected:"+e.getMessage()+".Aborting, Please try again", 3000,
                    Notification.Position.BOTTOM_CENTER);
        }
        catch (Exception e) {
            Notification.show("Error detected:"+e.getMessage()+".Aborting, Please try again", 3000,
                    Notification.Position.BOTTOM_CENTER);
        }

    }
    public MainView() {
        jsonTextArea = new TextArea("Please provide a json entity to create or update");
        // Resize:
        jsonTextArea.setWidth("80%");
        jsonTextArea.setHeight("50%");
        jsonTextArea.setPlaceholder("Enter a json advertisement input to perform CRUD or select from the list");
        jsonTextArea.setValueChangeMode(ValueChangeMode.LAZY);

        createButton = new Button(createOperation);
        updateButton = new Button(updateOperation);
        deleteButton = new Button(deleteOperation);
        jumpToTop = new Button(jumpOperation);

        createButton.addClickListener(event ->
            performCRUD(event.getSource()));
        updateButton.addClickListener(event -> performCRUD(event.getSource()));
        deleteButton.addClickListener(event -> performCRUD(event.getSource()));
        jumpToTop.addClickListener(event -> performCRUD(event.getSource()));


        FormLayout formLayout = new FormLayout();

        formLayout.add(jsonTextArea,categories, createButton,updateButton,deleteButton,jumpToTop);

        H1 titleLabel = new H1(pageLabelText);
        grid = ViewsUtils.buildGenericGrid(allAds);
        setOnClickHandler(grid);

        add(titleLabel);
        add(ViewsUtils.buildTopMenu());
        add(formLayout);
        add(grid);
    }

    private void validateJson() {
        try {
            // Parse the JSON entered by the user
            String jsonString = jsonTextArea.getValue();
            JSONTokener tokener = new JSONTokener(jsonString);
            JSONObject jsonObject = new JSONObject(tokener);

            // Write the JSON to the bean object
            binder.writeBean(bean);

        } catch (JSONException e) {
            // Invalid JSON format
            jsonTextArea.setErrorMessage("Invalid JSON format: " + e.getMessage());
            jsonTextArea.setInvalid(true);
        } catch (ValidationException e) {
            // Validation failed, show error message...
            jsonTextArea.setErrorMessage(e.getMessage());
            jsonTextArea.setInvalid(true);
        }
    }

    /**
     * Private class to validate the given input as json
     */
    private static class JsonBean {
        @javax.validation.constraints.NotNull(message = "JSON Input is Required")
        @com.fasterxml.jackson.annotation.JsonProperty("json")
        private String json;

        public String getJson() {
            return json;
        }

        public void setJson(String json) {
            this.json = json;
        }
    }
}

