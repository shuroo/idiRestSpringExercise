package view;

import com.directinsuranceexercise.rest.controller.AdvertisementController;
import com.directinsuranceexercise.rest.model.GenericAdvertisement;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@Route("/advertisementsView")
public class AdvertisementView extends VerticalLayout {
    private final RestTemplate restTemplate;
    @Autowired
    AdvertisementController advertisementController;

    public AdvertisementView(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        Grid<GenericAdvertisement> grid = new Grid<>();
        grid.setItems(advertisementController.getAdvertisementsList());
        grid.addColumn(GenericAdvertisement::getId).setHeader("ID");
        grid.addColumn(GenericAdvertisement::getCategory).setHeader("Category");
        grid.addColumn(GenericAdvertisement::getPrice).setHeader("Price");

        add(grid);
    }
}

