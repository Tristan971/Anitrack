package moe.anitrack.gui.views.providerselection.provider;

import org.springframework.stereotype.Component;

import moe.tristan.easyfxml.api.FxmlController;
import moe.tristan.easyfxml.api.FxmlFile;
import moe.tristan.easyfxml.api.FxmlNode;

@Component
public class ProviderPanelComponent implements FxmlNode {

    @Override
    public FxmlFile getFile() {
        return () -> "providerselection/provider/ProviderPanel.fxml";
    }

    @Override
    public Class<? extends FxmlController> getControllerClass() {
        return ProviderPanelController.class;
    }

}
