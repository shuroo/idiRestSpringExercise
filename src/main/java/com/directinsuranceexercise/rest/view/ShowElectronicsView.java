package com.directinsuranceexercise.rest.view;


import com.directinsuranceexercise.rest.model.AdManager;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import com.directinsuranceexercise.rest.utilities.ViewsUtils;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

/**
 * ShowElectronicsView is a Vaadin view to display a grid of electronic-advertisements
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

    /**
     * Simple constructor to build the electronics grid View as mentioned above:
     *  - Displays a title
     *  - A top menu
     *  - And the electronics advertisements grid.
     */
    public ShowElectronicsView() {
        add(ViewsUtils.constructTopLabel(titleLabel));
        add(ViewsUtils.buildTopMenu());
        add(ViewsUtils.buildElectronicsGrid(allAds));
    }


}
