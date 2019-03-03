package moe.anitrack.gui.view.views.authentication.field;

import org.springframework.stereotype.Component;

import moe.tristan.easyfxml.api.FxmlController;
import moe.tristan.easyfxml.api.FxmlFile;
import moe.tristan.easyfxml.api.FxmlNode;

@Component
public class AuthenticationFormFieldComponent implements FxmlNode {

    @Override
    public FxmlFile getFile() {
        return () -> "authentication/field/AuthenticationFormField.fxml";
    }

    @Override
    public Class<? extends FxmlController> getControllerClass() {
        return AuthenticationFormFieldController.class;
    }

}
