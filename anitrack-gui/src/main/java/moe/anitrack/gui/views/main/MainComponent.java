package moe.anitrack.gui.views.main;

import org.springframework.stereotype.Component;

import moe.tristan.easyfxml.api.FxmlController;
import moe.tristan.easyfxml.api.FxmlFile;
import moe.tristan.easyfxml.api.FxmlNode;

@Component
public class MainComponent implements FxmlNode {

    @Override
    public FxmlFile getFile() {
        return () -> "main/Main.fxml";
    }

    @Override
    public Class<? extends FxmlController> getControllerClass() {
        return MainController.class;
    }

}
