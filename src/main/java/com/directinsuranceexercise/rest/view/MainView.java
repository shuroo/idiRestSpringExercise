/**
 * This class represents the main view of the application, which displays and allows CRUD operations on advertisements.
 * The view contains a grid that shows all advertisements, a form with a text area that allows the user to input a JSON object
 * representing an advertisement, and buttons for creating, updating, and deleting advertisements.
 *
 * @author shirirave
 * @since 18/04/2023
 */

package com.directinsuranceexercise.rest.view;

import com.directinsuranceexercise.rest.model.*;
import com.directinsuranceexercise.rest.utilities.AdvertisementUtils;
import com.directinsuranceexercise.rest.utilities.Constants;
import com.directinsuranceexercise.rest.utilities.ViewsUtils;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
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

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

@Route(value = "/")
public class MainView extends VerticalLayout {

    private Grid<GenericAdvertisement> grid;

    private ConcurrentLinkedQueue<GenericAdvertisement> allAds = AdManager.getInstance().getAdvertisements();

    private static final java.util.logging.Logger logger = Logger.getLogger(AdvertisementUtils.class.getName());
    final String pageLabelText = "CRUD Advertisements";

    private final String createOperation = "CREATE";
    private final String updateOperation = "UPDATE";
    private final String deleteOperation = "DELETE";
    private final String jumpToTopOperation = "JumpToTop";

    private static final String invalidCategoryMsg = "Category is invalid, Please choose a category";
    private static final String jsonInputRequredMsg = "JSON Input is Required";

    private static final String deleteConfirmationMsg = "Are you sure you want to delete this advertisement?";
    private final TextArea jsonTextArea;
    private final Button createButton;
    private final Button updateButton;
    private final Button deleteButton;
    private final Button jumpToTop;

    private final ComboBox<String> categories = ViewsUtils.getCategoryOptions();
    private RestTemplate restTemplate = new RestTemplate();
    private final Binder<JsonBean> binder = new Binder<>(JsonBean.class);
    private final JsonBean bean = new JsonBean();


    /**
     * Convert an AssetAdvertisement to a JSONObject, and add its fields to the existing JSONObject.
     *
     * @param jsonAd - The JSONObject to update with the advertisement fields.
     * @param ad - The AssetAdvertisement to convert to a JSONObject.
     * @throws JSONException if any errors occur while converting
     *
     * */

    private void genericAdvertisementAsJson(JSONObject jsonAd, GenericAdvertisement ad) throws JSONException {
        jsonAd.put(Constants.id, ad.getId());
        jsonAd.put(Constants.price, ad.getPrice());
        jsonAd.put(Constants.contactName, ad.getContactName());
        jsonAd.put(Constants.contactPhoneNumber, ad.getContactPhoneNumber());
    }

    /**
     * Converts an AssetAdvertisement object to JSON format and adds its fields to the given JSONObject.
     *
     * @param jsonAd - the JSONObject to which the advertisement fields will be added
     * @param ad - the AssetAdvertisement object to convert to JSON format
     * @throws JSONException if there is an error in the JSON object
     */
    private void assetAdvertisementAsJson(JSONObject jsonAd, AssetAdvertisement ad) throws JSONException {
        jsonAd.put(Constants.numberOfRooms, ad.getNumberOfRooms());
        jsonAd.put(Constants.assetAdType, ad.getAssetAdType());
        jsonAd.put(Constants.assetSize, ad.getAssetSize());
    }

    /**
     * Converts a CarAdvertisement object to JSON format and adds its fields to the given JSONObject.
     *
     * @param jsonAd - the JSONObject to which the advertisement fields will be added
     * @param ad - the CarAdvertisement object to convert to JSON format
     * @throws JSONException if there is an error in the JSON object
     */
    private void carAdvertisementAsJson(JSONObject jsonAd, CarAdvertisement ad) throws JSONException {
        jsonAd.put(Constants.color, ad.getColor());
        jsonAd.put(Constants.model, ad.getPrice());
        jsonAd.put(Constants.manufacturer, ad.getManufacturer());
        jsonAd.put(Constants.year, ad.getYear());
        jsonAd.put(Constants.km, ad.getKm());
    }

    /**
     * Converts an ElectricityAdvertisement object to JSON format and adds its fields to the given JSONObject.
     *
     * @param jsonAd - the JSONObject to which the advertisement fields will be added
     * @param ad - the ElectricityAdvertisement object to convert to JSON format
     * @throws JSONException if there is an error in the JSON object
     */
    private void electronicsAdvertisementAsJson(JSONObject jsonAd, ElectricityAdvertisement ad) throws JSONException {
        jsonAd.put(Constants.condition, ad.getCondition());
        jsonAd.put(Constants.electricityType, ad.getElectricityType());
    }

    /**
     * Converts a GenericAdvertisement object to JSON format by calling the appropriate private method
     * based on the advertisement's category and returns the resulting JSONObject.
     *
     * @param ad - the GenericAdvertisement object to convert to JSON format
     * @param extendedAd - the extended advertisement object to convert to JSON format
     * @return a JSONObject containing the advertisement's fields in JSON format
     */
    public JSONObject buildAdvertisementAsJson(GenericAdvertisement ad, GenericAdvertisement extendedAd) {
        JSONObject jsonAd = new JSONObject();
        String adCategory = ad.getCategory();
        try {
            genericAdvertisementAsJson(jsonAd, ad);
            if (adCategory.equals(Constants.assetCategory)) {
                assetAdvertisementAsJson(jsonAd, (AssetAdvertisement) extendedAd);

            } else if (adCategory.equals(Constants.carCategory)) {
                carAdvertisementAsJson(jsonAd, (CarAdvertisement) extendedAd);

            } else if (adCategory.equals(Constants.electricityCategory)) {
                electronicsAdvertisementAsJson(jsonAd, (ElectricityAdvertisement) extendedAd);

            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return jsonAd;
    }

    private void setOnClickHandler(Grid<GenericAdvertisement> grid) {
        grid.addItemClickListener(event -> {
            // get the selected row's data as a JsonObject
            GenericAdvertisement ad = event.getItem();
            // Get the advertisement with its specific fields and structure of the sub-classes ( car, asset , etc).
            GenericAdvertisement extendedAd = AdvertisementUtils.findById(ad.getId(), allAds);
            JSONObject jsonAd = buildAdvertisementAsJson(ad, extendedAd);

            // set the JSON string as the text of the text area
            jsonTextArea.setValue(jsonAd.toString());
            categories.setValue(ad.getCategory());
        });
    }

    private HttpMethod matchHttpMethod(String httpMethodString) {

        switch (httpMethodString) {
            case createOperation:
                return HttpMethod.POST;
            case updateOperation:
                return HttpMethod.PUT;
            case deleteOperation:
                return HttpMethod.DELETE;
            case jumpToTopOperation:
                return HttpMethod.GET;
        }

        return HttpMethod.POST;

    }

    private String buildSuffixByMethod(String httpMethod, JSONObject requestBody) throws JSONException {

        if (!httpMethod.equals(createOperation)) {
            String id = requestBody.getString("id");
            return id;
        }

        return createOperation.toLowerCase();
    }

    private String buildUrlPrefixByCategory() throws JSONException {
        String urlPrefix = Constants.emptyString;

        String category = categories.getValue();
        switch (category) {
            case Constants.genericCategory:
                throw new JSONException(invalidCategoryMsg);
            case Constants.assetCategory:
                urlPrefix = "assetAdvertisements";
                return urlPrefix;
            case Constants.carCategory:
                urlPrefix = "carAdvertisements";
                return urlPrefix;
            case Constants.electricityCategory:
                urlPrefix = "electricityAdvertisements";
                return urlPrefix;

            // other categories can be added here in the future.
        }

        return urlPrefix;
    }

    /**
     * The following method is aimed to validate the full json structure by its category.
     * Will be used when the full json is required as an input, AKA, for create and update operations.
     * Throws a json exception in case a required field does not exist.
     * ( When additional categories are added, should be updated ).
     *
     * @param requestBody - The request body in a json format
     * @return Boolean - True if valid, False otherwise
     * @throws JSONException - When a required field is missing etc.
     */
    private boolean validateFullJsonStructure(JSONObject requestBody) throws JSONException {
        requestBody.getDouble(Constants.price);
        requestBody.getString(Constants.contactName);
        requestBody.getString(Constants.contactPhoneNumber);
        String category = categories.getValue();
        switch (category) {
            case Constants.genericCategory:
                Notification.show(invalidCategoryMsg, 3000, Notification.Position.BOTTOM_CENTER);
                return false;
            case Constants.assetCategory:
                requestBody.getInt(Constants.assetSize);
                requestBody.getInt(Constants.numberOfRooms);
                return true;
            case Constants.carCategory:
                requestBody.getString(Constants.color);
                requestBody.getString(Constants.model);
                requestBody.getString(Constants.manufacturer);
                requestBody.getInt(Constants.year);
                requestBody.getInt(Constants.km);
                return true;
            case Constants.electricityCategory:
                requestBody.getString(Constants.electricityType);
                requestBody.getString(Constants.condition);
                return true;
        }

        return true;
    }

    /**
     * Validates a request based on the HTTP method and the JSON request body,
     * Make sure the request has all the needed fields.
     *
     * @param requestBody the JSON request body
     * @param httpMethod the HTTP method of the request
     * @return true if the request is valid, false otherwise
     * @throws JSONException if there is an error parsing the JSON
     */
    private boolean validateRequest(JSONObject requestBody, String httpMethod) throws JSONException {
        // For any request except creation, we should verify the id field exists.
        if (!httpMethod.equals(createOperation)) {
            requestBody.getString(Constants.id);
        }
        // For 'Create' or 'Edit', verify that we have a full JSON structure with all the Ad required fields.
        if (httpMethod.equals(createOperation) || httpMethod.equals(updateOperation)) {
            return validateFullJsonStructure(requestBody);
        }

        return true;
    }

    /**
     * Builds a URL from the given URL prefix and suffix.
     * @param urlPrefix the URL prefix
     * @param urlSuffix the URL suffix
     * @return the built URL
     */
    private String buildUrl(String urlPrefix, String urlSuffix){
        return Constants.baseUrl + urlPrefix + "/" + urlSuffix;
    }

    /**
     * Perform CRUD (+ jumpTo The top of the list ) operations based on the selected operation, if the input is valid.
     * @param operationBtn - The Operation button clicked ( Create \ Update \ Delete \ JumpToTop )
     */
    private void performCRUD(Button operationBtn) {

        validateJson();
        String jsonString = jsonTextArea.getValue();
        JSONTokener tokener = new JSONTokener(jsonString);
        JSONObject requestBody = null;

        try {
            requestBody = new JSONObject(tokener);

            // Send the request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String category = categories.getValue();
            requestBody.put(Constants.category, category);
            HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

            HttpMethod method = null;
            String urlSuffix = null;
            String urlPrefix = null;

            String httpMethodString = operationBtn.getText();
            validateRequest(requestBody, httpMethodString);

            urlPrefix = buildUrlPrefixByCategory();
            urlSuffix = buildSuffixByMethod(httpMethodString, requestBody);

            method = matchHttpMethod(httpMethodString);
            String url = buildUrl(urlPrefix,urlSuffix);

            logger.info("Sending request of type:" + httpMethodString + " to url:" + url);
            ResponseEntity<String> response = restTemplate.exchange(url, method, entity, String.class);

            // Reload the grid with the updated data
            allAds = AdManager.getInstance().getAdvertisements();
            grid.setItems(allAds);

            // Check the response status code
            if (response.getStatusCode() == HttpStatus.OK) {
                Notification.show("Successfully sending http request:" + method + ". Response body was:" + response.getBody(), 3000,
                        Notification.Position.BOTTOM_CENTER);
            } else {
                Notification.show("Error sending http request:" + method + ".Detected status:" + response.getStatusCode() + ". aborting.", 3000,
                        Notification.Position.BOTTOM_CENTER);
            }


        } catch (JSONException e) {
            e.printStackTrace();
            Notification.show(AdvertisementUtils.generalErrorMsg(e), 3000,
                    Notification.Position.BOTTOM_CENTER);
        } catch (Exception e) {
            e.printStackTrace();
            Notification.show(AdvertisementUtils.generalErrorMsg(e), 3000,
                    Notification.Position.BOTTOM_CENTER);
        }

    }


    /**
     * Sets width and height of the text area used to display and input JSON data.
     * The width is set to 80% of the available horizontal space,
     * The height is set to 70% of the available vertical space.
     */

    private void setTextAreaSize() {
        jsonTextArea.setWidth("80%");
        jsonTextArea.setHeight("70%");
    }

    /**
     * The constructor for the MainView class.
     * Constructs the Main View Page:
     *  - Initializes the UI components of the view, including a text area for inputting and displaying JSON data,
     *    buttons for performing CRUD operations, and a grid for displaying existing advertisements.
     *  - sets up event listeners for the buttons and grid.
     */

    public MainView() {
        jsonTextArea = new TextArea();
        // Resize:
        setTextAreaSize();
        jsonTextArea.setLabel("Enter a json advertisement input to perform CRUD operations or select from the list");
        jsonTextArea.setValueChangeMode(ValueChangeMode.LAZY);

        createButton = new Button(createOperation);
        updateButton = new Button(updateOperation);
        deleteButton = new Button(deleteOperation);
        jumpToTop = new Button(jumpToTopOperation);

        createButton.addClickListener(event ->
                performCRUD(event.getSource()));
        updateButton.addClickListener(event -> performCRUD(event.getSource()));
        deleteButton.addClickListener(event -> performCRUD(event.getSource()));
        jumpToTop.addClickListener(event -> performCRUD(event.getSource()));


        FormLayout formLayout = new FormLayout();

        formLayout.add(jsonTextArea, categories, createButton, updateButton, deleteButton, jumpToTop);

        H2 titleLabel = ViewsUtils.constructTopLabel(pageLabelText);
        grid = ViewsUtils.buildGenericGrid(allAds.stream().toList());
        setOnClickHandler(grid);

        add(titleLabel);
        add(ViewsUtils.buildTopMenu());
        add(formLayout);
        add(grid);
    }

    /**
     * Method to validate the input as a json. Makes sure that the input has a valid json structure.
     * Raising ErrorMessage for the text field when a jsonException occurs ( if the input is not in a proper json format )
     * @return boolean - True if the json input is valid, false otherwise.
     */
    private boolean validateJson() {
        try {
            // Parse the JSON entered by the user
            String jsonString = jsonTextArea.getValue();
            JSONTokener tokener = new JSONTokener(jsonString);
            JSONObject jsonObject = new JSONObject(tokener);

            // Write the JSON to the bean object
            binder.writeBean(bean);
            return true;

        } catch (JSONException e) {
            // Invalid JSON format
            jsonTextArea.setErrorMessage("Invalid JSON format: " + e.getMessage());
            jsonTextArea.setInvalid(true);
            return false;
        } catch (ValidationException e) {
            // Validation failed, show error message...
            jsonTextArea.setErrorMessage(e.getMessage());
            jsonTextArea.setInvalid(true);
            return false;
        }
    }

    /**
     * Private class to validate the given input as json
     */
    private static class JsonBean {
        @javax.validation.constraints.NotNull(message = jsonInputRequredMsg)
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

