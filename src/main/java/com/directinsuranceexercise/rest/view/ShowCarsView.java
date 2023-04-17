package com.directinsuranceexercise.rest.view;


import com.directinsuranceexercise.rest.model.AdManager;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import com.directinsuranceexercise.rest.utilities.ViewsUtils;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

/**
 * ShowCarsView is a Vaadin view that displays a grid of advertisements
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

    public ShowCarsView(){
        add(ViewsUtils.addTopLabel(titleLabel));
        add(ViewsUtils.buildTopMenu());
        add(ViewsUtils.buildCarGrid(allAds));
    }


}
