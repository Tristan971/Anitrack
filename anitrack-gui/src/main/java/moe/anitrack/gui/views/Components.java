package moe.anitrack.gui.views;

import moe.anitrack.gui.views.root.Root;
import moe.tristan.easyfxml.api.FxmlController;
import moe.tristan.easyfxml.api.FxmlFile;
import moe.tristan.easyfxml.api.FxmlNode;

public enum Components implements FxmlNode {
    ROOT("root/Root.fxml", Root.class);

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
