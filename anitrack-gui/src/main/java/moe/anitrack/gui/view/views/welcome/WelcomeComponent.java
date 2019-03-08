package moe.anitrack.gui.view.views.welcome;

import org.springframework.stereotype.Component;

import moe.tristan.easyfxml.api.FxmlController;
import moe.tristan.easyfxml.api.FxmlFile;
import moe.tristan.easyfxml.api.FxmlNode;

@Component
public class WelcomeComponent implements FxmlNode {

    @Override
    public FxmlFile getFile() {
        return () -> "welcome/Welcome.fxml";
    }

    @Override
    public Class<? extends FxmlController> getControllerClass() {
        return WelcomeController.class;
    }

}
