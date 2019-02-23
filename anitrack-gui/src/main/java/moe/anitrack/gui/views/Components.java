package moe.anitrack.gui.views;

import moe.anitrack.gui.views.authentication.AuthenticationController;
import moe.anitrack.gui.views.providerselection.ProviderSelectPanelController;
import moe.anitrack.gui.views.providerselection.ProviderSelectionController;
import moe.anitrack.gui.views.root.Root;
import moe.tristan.easyfxml.api.FxmlController;
import moe.tristan.easyfxml.api.FxmlFile;
import moe.tristan.easyfxml.api.FxmlNode;

public enum Components implements FxmlNode {
    ROOT("root/Root.fxml", Root.class),

    AUTHENTICATION_FORM("authentication/Authentication.fxml", AuthenticationController.class),

    PROVIDER_SELECTION("providerselection/ProviderSelection.fxml", ProviderSelectionController.class),
    PROVIDER_SELECTION_PANE("providerselection/ProviderSelectPanel.fxml", ProviderSelectPanelController.class);

    private final String fxmlFilePath;
    private final Class<? extends FxmlController> controllerClass;

    Components(String fxmlFilePath, Class<? extends FxmlController> controllerClass) {
        this.fxmlFilePath = fxmlFilePath;
        this.controllerClass = controllerClass;
    }

    @Override
    public FxmlFile getFile() {
        return () -> fxmlFilePath;
    }

    @Override
    public Class<? extends FxmlController> getControllerClass() {
        return controllerClass;
    }

}
