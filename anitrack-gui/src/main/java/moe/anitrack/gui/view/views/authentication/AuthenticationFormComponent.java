package moe.anitrack.gui.view.views.authentication;

import org.springframework.stereotype.Component;

import moe.tristan.easyfxml.api.FxmlController;
import moe.tristan.easyfxml.api.FxmlFile;
import moe.tristan.easyfxml.api.FxmlNode;

@Component
public class AuthenticationFormComponent implements FxmlNode {

    @Override
    public FxmlFile getFile() {
        return () -> "authentication/AuthenticationForm.fxml";
    }

    @Override
    public Class<? extends FxmlController> getControllerClass() {
        return AuthenticationFormController.class;
    }

}
