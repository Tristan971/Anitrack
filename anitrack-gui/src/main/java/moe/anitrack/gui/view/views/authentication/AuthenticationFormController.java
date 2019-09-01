package moe.anitrack.gui.view.views.authentication;

import static javafx.application.Platform.runLater;
import static moe.tristan.easyfxml.util.Buttons.setOnClick;
import static moe.tristan.easyfxml.util.Properties.bind;
import static moe.tristan.easyfxml.util.Properties.whenPropertyIsSet;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import moe.anitrack.gui.view.views.authentication.field.AuthenticationFormFieldComponent;
import moe.anitrack.gui.view.views.authentication.field.AuthenticationFormFieldController;
import moe.anitrack.thirdparties.common.ThirdpartyAuthenticationService;
import moe.anitrack.thirdparties.common.model.authentication.AuthenticationField;
import moe.tristan.easyfxml.EasyFxml;
import moe.tristan.easyfxml.fxkit.form.FormController;
import moe.tristan.easyfxml.model.exception.ExceptionHandler;

@Component
public class AuthenticationFormController extends FormController {

    @FXML
    private Label serviceName;
    @FXML
    private VBox fieldsBox;
    @FXML
    private Button submitButton;
    @FXML
    public ProgressIndicator loginProgressIndicator;

    private final EasyFxml easyFxml;
    private final AuthenticationFormFieldComponent fieldComponent;

    private Property<ThirdpartyAuthenticationService<?>> authenticationServiceProperty;
    private final Map<AuthenticationField, String> fieldValues = new ConcurrentHashMap<>();

    public AuthenticationFormController(EasyFxml easyFxml, AuthenticationFormFieldComponent fieldComponent) {
        this.easyFxml = easyFxml;
        this.fieldComponent = fieldComponent;
    }

    @Override
    public void initialize() {
        whenPropertyIsSet(authenticationServiceProperty, service -> runLater(() -> prepareAuthentication(service)));
    }

    @Override
    public void submit() {
        loginProgressIndicator.setVisible(true);
        authenticationServiceProperty.getValue().tryAuthenticate(fieldValues).whenCompleteAsync((res, err) -> {
            loginProgressIndicator.setVisible(false);
            if (err != null) {
                ExceptionHandler.displayExceptionPane("Failed to authenticate.", err.getMessage(), err);
            } else {

            }
        }, Platform::runLater);
    }

    private void prepareAuthentication(ThirdpartyAuthenticationService<?> authenticationService) {
        final List<AuthenticationField> fields = authenticationService.getAuthenticationFields();

        bind(authenticationServiceProperty, ThirdpartyAuthenticationService::getName, serviceName.textProperty());

        fields.forEach(field -> easyFxml.load(
                fieldComponent,
                Pane.class,
                AuthenticationFormFieldController.class
        ).afterControllerLoaded(fieldController -> {
            fieldController.setField(field);
            fieldController.setOnFieldUpdated(value -> fieldValues.put(field, value));
        }).afterNodeLoaded(fieldsBox.getChildren()::add));

        setOnClick(submitButton, this::submit);
    }

}
