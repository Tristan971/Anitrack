package moe.anitrack.gui.view.views.providerselection;

import org.springframework.stereotype.Component;

import moe.tristan.easyfxml.api.FxmlController;
import moe.tristan.easyfxml.api.FxmlFile;
import moe.tristan.easyfxml.api.FxmlNode;

@Component
public class ProviderSelectionComponent implements FxmlNode {

    @Override
    public FxmlFile getFile() {
        return () -> "providerselection/ProviderSelection.fxml";
    }

    @Override
    public Class<? extends FxmlController> getControllerClass() {
        return ProviderSelectionController.class;
    }

}
