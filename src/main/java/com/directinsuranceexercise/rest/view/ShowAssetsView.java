package com.directinsuranceexercise.rest.view;


import com.directinsuranceexercise.rest.model.AdManager;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import com.directinsuranceexercise.rest.utilities.ViewsUtils;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

/**
 * ShowAssetsView is a Vaadin view that displays a grid of advertisements
 * retrieved from a singleton AdManager object, along with a top menu.
 * The view is accessed through the "/showAssets" route.
 *
 * @author shirirave
 * @version 1.0
 * @since 17/04/2023
 */
@Route(value = "/showAssets")
public class ShowAssetsView extends VerticalLayout {

    private List<GenericAdvertisement> allAds = AdManager.getInstance().getAdvertisements().stream().toList();
    private final String titleLabel = "Asset Advertisements";

    public ShowAssetsView() {
        add(ViewsUtils.addTopLabel(titleLabel));
        add(ViewsUtils.buildTopMenu());
        add(ViewsUtils.buildAssetGrid(allAds));
    }


}
