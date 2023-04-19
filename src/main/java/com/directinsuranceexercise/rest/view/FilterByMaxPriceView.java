package com.directinsuranceexercise.rest.view;

import com.directinsuranceexercise.rest.model.AdManager;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import com.directinsuranceexercise.rest.utilities.AdvertisementUtils;
import com.directinsuranceexercise.rest.utilities.Constants;
import com.directinsuranceexercise.rest.utilities.ViewsUtils;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToDoubleConverter;
import com.vaadin.flow.data.validator.DoubleRangeValidator;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;

/**
 * This class represents a view that allows the user to filter the ads by a maximum price.
 * It is also using Vaadin Grid to display the ads.
 *
 * @author shirirave
 * @since 18/04/2023
 */
@Route(value = "/filterByMaxPrice")
public class FilterByMaxPriceView extends VerticalLayout {
    private final RestTemplate restTemplate;

    private Logger logger = Logger.getLogger(FilterByMaxPriceView.class.getName());

    //todo: allow numbers only!
    private TextField maxPriceField;
    private Button filterButton;

    private final String maxPriceTextFieldLbl = "Max Price";

    private final double minValidatorRange = 0.0;

    private final double maxValidatorRange = 1000000.0;

    private final static String numberWasInvalidMsg = "Please enter a valid number";

    private final static String outOfRangeMsg = "Price must be between 0 and 1,000,000";

    private final String titleLabel = "Filter By Max Price";

    /**
     * This is an instanse of the singletop data structure of the adds list
     * ( -implemented by a concurrentListQueue for ad syncronization reasons )
     */
    private List<GenericAdvertisement> allAds = AdManager.getInstance().getAdvertisements().stream().toList();
    private Grid<GenericAdvertisement> grid;

    /**
     * This method filters the ads by the maximum price entered by the user.
     * If the input value is invalid, a warning is logged and nothing happens.
     */
    private void filterByMaxPrice() {
        if (maxPriceField.getValue() == null) {
            logger.warning("Bad initial value for max number, failed to filter");
            return;
        }
        Double maxPrice = null;
        try {
            maxPrice = Double.parseDouble(maxPriceField.getValue());
        } catch (NumberFormatException e) {
            logger.warning("Failed to filter by max price.Max field is not set to a valid value, please try again");
            return;
        }

        List<GenericAdvertisement> filteredAds = AdvertisementUtils.filterByMaxPrice(maxPrice, allAds);
        grid.setItems(filteredAds);
    }

    /**
     * This is the constructor for the FilterByMaxPriceView class.
     * It initializes the text field, button, and grid.
     *
     * @param restTemplate The RestTemplate used for HTTP requests.
     */
    public FilterByMaxPriceView(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

        // Initialize text field, binder, and button
        maxPriceField = new TextField("Max Price");
        Binder<Double> maxPriceBinder = new Binder<>();
        maxPriceBinder.forField(maxPriceField)
                .withNullRepresentation(Constants.emptyString)
                .withConverter(new StringToDoubleConverter(numberWasInvalidMsg))
                .withValidator(new DoubleRangeValidator(outOfRangeMsg, 0.0, 1000000.0))
                .bind(maxPriceBindery -> null, (maxPriceBindery, value) -> {
                    // Do nothing here, just need a write-only binding
                });

        filterButton = new Button(Constants.filterBtnTitle, e -> filterByMaxPrice());

        // Create the grid and add to the view
        grid = ViewsUtils.buildGenericGrid(allAds);
        add(ViewsUtils.constructTopLabel(titleLabel));
        add(ViewsUtils.buildTopMenu());
        add(ViewsUtils.createFilterByComponent(filterButton, maxPriceField), grid);
    }

}

