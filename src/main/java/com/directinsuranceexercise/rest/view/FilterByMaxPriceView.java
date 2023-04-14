package com.directinsuranceexercise.rest.view;

import com.directinsuranceexercise.rest.model.AdManager;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import com.directinsuranceexercise.rest.utilities.AdvertisementUtils;
import com.directinsuranceexercise.rest.utilities.Constants;
import com.directinsuranceexercise.rest.utilities.ViewsUtils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToDoubleConverter;
import com.vaadin.flow.data.validator.DoubleRangeValidator;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

///@CssImport(value = "../styles.css", themeFor = "vaadin-button")
    @Route(value = "/filterByMaxPrice")
    public class FilterByMaxPriceView extends VerticalLayout {
        private final RestTemplate restTemplate;

        private Logger logger = Logger.getLogger(FilterByMaxPriceView.class.getName());

        //todo: allow numbers only!
        private TextField maxPriceField;
        private Button filterButton;

        private List<GenericAdvertisement> allAds = AdManager.getInstance().getAdvertisements().stream().toList();

        private Grid<GenericAdvertisement> grid;

        private void filterByMaxPrice() {
            if(maxPriceField.getValue() == null){
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

        public FilterByMaxPriceView(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;

            // Initialize dropdown list, max price field, and filter button
            maxPriceField = new TextField("Max Price");
            Binder<Double> maxPriceBinder = new Binder<>();
            maxPriceBinder.forField(maxPriceField)
                    .withNullRepresentation("")
                    .withConverter(new StringToDoubleConverter("Please enter a valid number"))
                    .withValidator(new DoubleRangeValidator("Price must be between 0 and 1,000,000", 0.0, 1000000.0))
                    .bind(maxPriceBindery -> null, (maxPriceBindery, value) -> {
                        // Do nothing here, just need a write-only binding
                        //todo: what's here?
                    });

            filterButton = new Button("Filter!", e -> filterByMaxPrice());

            // Create the grid and add to the view
            grid = ViewsUtils.buildGenericGrid(allAds);
            //todo: handle strings
            Button goToFilterByCategoryButton = new Button("GoTo FilterByCategory");
            goToFilterByCategoryButton.addClickListener(event -> UI.getCurrent().navigate(FilterByCategoryView.class));
            add(ViewsUtils.createFilterByComponent( "Filter by Max Price",
                    goToFilterByCategoryButton,
                    filterButton,
                    Optional.ofNullable(maxPriceField)), grid);
            ViewsUtils.setBorder(this);
        }
    }
