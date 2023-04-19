package com.directinsuranceexercise.rest.view;


import com.directinsuranceexercise.rest.model.AdManager;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import com.directinsuranceexercise.rest.utilities.ViewsUtils;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

/**
 * ShowCarsView is a Vaadin view to display a grid of car advertisements
 * retrieved from a singleton AdManager object, along with a top menu.
 * The view is accessed through the "/showCars" route.
 *
 * @author shirirave
 * @version 1.0
 * @since 17/04/2023
 */
@Route(value = "/showCars")
public class ShowCarsView extends VerticalLayout {

    private List<GenericAdvertisement> allAds = AdManager.getInstance().getAdvertisements().stream().toList();
    private final String titleLabel = "Car Advertisements";

    /**
     * Simple constructor to build the car grid view as mentioned above:
     *  - Displays a title
     *  - A top menu
     *  - And the car advertisements grid.
     */
    public ShowCarsView() {
        add(ViewsUtils.constructTopLabel(titleLabel));
        add(ViewsUtils.buildTopMenu());
        add(ViewsUtils.buildCarGrid(allAds));
    }


}
