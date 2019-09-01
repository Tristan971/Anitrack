package moe.anitrack.gui.view.views.authentication.field;

import org.springframework.stereotype.Component;

import moe.tristan.easyfxml.api.FxmlComponent;
import moe.tristan.easyfxml.api.FxmlController;
import moe.tristan.easyfxml.api.FxmlFile;

@Component
public class AuthenticationFormFieldComponent implements FxmlComponent {

    @Override
    public FxmlFile getFile() {
        return () -> "AuthenticationFormField.fxml";
    }

    @Override
    public Class<? extends FxmlController> getControllerClass() {
        return AuthenticationFormFieldController.class;
    }

}
