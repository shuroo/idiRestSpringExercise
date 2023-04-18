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


@Route(value = "/filterByCategory")
public class FilterByCategoryView extends VerticalLayout {
    private final RestTemplate restTemplate;
    private ComboBox<String> categoryDropdown;
    private Button filterButton;

    private List<GenericAdvertisement> allAds = AdManager.getInstance().getAdvertisements().stream().toList();

    private final String pageLabelText = "Filter by Category";

    private final String filterTitle = "Filter!";

    private Grid<GenericAdvertisement> grid;

    private void filterByCategory() {
        String category = categoryDropdown.getValue();
        if (category.equals(Constants.genericCategory)) {
            grid.setItems(allAds);
        } else {
            List<GenericAdvertisement> filteredAds = AdvertisementUtils.filterByCategory(category, allAds);
            grid.setItems(filteredAds);
        }
    }


    public FilterByCategoryView(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;


        // Initialize dropdown list and filter button
        categoryDropdown = ViewsUtils.getCategoryOptions();
        filterButton = new Button(filterTitle, e -> filterByCategory());

        // Create the grid and add to the view

        grid = ViewsUtils.buildGenericGrid(allAds);

        add(ViewsUtils.addTopLabel(pageLabelText));
        add(ViewsUtils.buildTopMenu());
        add(ViewsUtils.createFilterByComponent(filterButton, categoryDropdown), grid);
    }
}

