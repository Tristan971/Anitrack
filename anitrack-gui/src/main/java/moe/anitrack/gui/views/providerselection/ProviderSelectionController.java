package moe.anitrack.gui.views.providerselection;

import org.springframework.stereotype.Component;

import moe.anitrack.thirdparties.common.ThirdpartyService;
import moe.tristan.easyfxml.api.FxmlController;

@Component
public class ProviderSelectionController implements FxmlController {

    private final ThirdpartyService thirdpartyServicesAvailable;

    public ProviderSelectionController(ThirdpartyService thirdpartyServicesAvailable) {
        this.thirdpartyServicesAvailable = thirdpartyServicesAvailable;
    }

    @Override
    public void initialize() {

    }

}
