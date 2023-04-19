package com.directinsuranceexercise.rest.view;

import com.directinsuranceexercise.rest.model.AdManager;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import com.directinsuranceexercise.rest.utilities.AdvertisementUtils;
import com.directinsuranceexercise.rest.utilities.Constants;
import com.directinsuranceexercise.rest.utilities.ViewsUtils;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/**
 * The FilterByCategoryView class represents the view for filtering advertisements by category.
 * It extends the VerticalLayout class and implements the route "/filterByCategory".
 * @author shirirave
 * @since 18/04/2023
 *
 */


@Route(value = "/filterByCategory")
public class FilterByCategoryView extends VerticalLayout {
    /**
     * The RestTemplate object used to communicate with the API.
     */
    private final RestTemplate restTemplate;

    /**
     * The ComboBox object representing the category dropdown.
     */
    private ComboBox<String> categoryDropdown;

    /**
     * The Button object representing the filter button.
     */
    private Button filterButton;

    /**
     * A list of all GenericAdvertisement objects.
     */
    private List<GenericAdvertisement> allAds = AdManager.getInstance().getAdvertisements().stream().toList();

    /**
     * The label text for the top of the page.
     */
    private final String pageLabelText = "Filter by Category";

    /**
     * The Grid object used to display advertisements.
     */
    private Grid<GenericAdvertisement> grid;

    /**
     * Filters the advertisements by category and sets the items in the grid.
     */
    private void filterByCategory() {
        String category = categoryDropdown.getValue();
        if (category.equals(Constants.genericCategory)) {
            grid.setItems(allAds);
        } else {
            List<GenericAdvertisement> filteredAds = AdvertisementUtils.filterByCategory(category, allAds);
            grid.setItems(filteredAds);
        }
    }

    /**
     * Constructs a FilterByCategoryView object.
     *
     * @param restTemplate The RestTemplate object used to communicate with the API.
     */
    public FilterByCategoryView(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

        // Initialize dropdown list and filter button
        categoryDropdown = ViewsUtils.getCategoryOptions();
        filterButton = new Button(Constants.filterBtnTitle, e -> filterByCategory());

        // Create the grid and add to the view
        grid = ViewsUtils.buildGenericGrid(allAds);

        add(ViewsUtils.constructTopLabel(pageLabelText));
        add(ViewsUtils.buildTopMenu());
        add(ViewsUtils.createFilterByComponent(filterButton, categoryDropdown), grid);
    }
}

