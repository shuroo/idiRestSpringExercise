package com.directinsuranceexercise.rest.view;


import com.directinsuranceexercise.rest.model.AdManager;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import com.directinsuranceexercise.rest.utilities.AdvertisementUtils;
import com.directinsuranceexercise.rest.utilities.ViewsUtils;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

/**
 * ShowElectronicsView is a Vaadin view that displays a grid of advertisements
 * retrieved from a singleton AdManager object, along with a top menu.
 * The view is accessed through the "/showElectronics" route.
 *
 * @author shirirave
 * @version 1.0
 * @since 17/04/2023
 */
@Route(value = "/showElectronics")
public class ShowElectronicsView extends VerticalLayout {

    private List<GenericAdvertisement> allAds = AdManager.getInstance().getAdvertisements().stream().toList();
    private final String titleLabel = "Electronic Advertisements";

    public ShowElectronicsView(){
        add(ViewsUtils.addTopLabel(titleLabel));
        add(ViewsUtils.buildTopMenu());
        add(ViewsUtils.buildElectronicsGrid(allAds));
    }


}
