package moe.anitrack.gui.view.views.authentication;

import static java.util.concurrent.CompletableFuture.runAsync;
import static moe.tristan.easyfxml.util.Buttons.setOnClick;
import static moe.tristan.easyfxml.util.Nodes.hideAndResizeParentIf;
import static moe.tristan.easyfxml.util.Properties.whenPropertyIsSet;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import moe.anitrack.gui.view.util.OwnStageAware;
import moe.anitrack.gui.view.views.authentication.field.AuthenticationFormFieldComponent;
import moe.anitrack.gui.view.views.authentication.field.AuthenticationFormFieldController;
import moe.anitrack.thirdparties.common.model.authentication.AuthenticationField;
import moe.anitrack.thirdparties.common.model.presentation.ThirdpartyServiceInfo;
import moe.tristan.easyfxml.EasyFxml;
import moe.tristan.easyfxml.api.FxmlController;
import moe.tristan.easyfxml.model.fxml.FxmlLoadResult;

@Component
@Scope(scopeName = SCOPE_PROTOTYPE)
public class AuthenticationFormController implements FxmlController, OwnStageAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFormController.class);

    @FXML
    private Label serviceName;

    @FXML
    private VBox fieldsBox;

    @FXML
    public Button submitButton;

    @FXML
    private ProgressIndicator loginProgressIndicator;

    private final EasyFxml easyFxml;
    private final AuthenticationFormFieldComponent formFieldComponent;

    private final Property<ThirdpartyServiceInfo> serviceInfo = new SimpleObjectProperty<>();
    private final Property<List<AuthenticationField>> formFields = new SimpleObjectProperty<>();

    private final BooleanProperty submitting = new SimpleBooleanProperty(false);
    private final Property<Consumer<Map<AuthenticationField, String>>> submitCallbackProp = new SimpleObjectProperty<>();
    private final Map<AuthenticationField, String> fieldValues = new HashMap<>();

    private Stage formStage;

    public AuthenticationFormController(EasyFxml easyFxml, AuthenticationFormFieldComponent formFieldComponent) {
        this.easyFxml = easyFxml;
        this.formFieldComponent = formFieldComponent;
    }

    @Override
    public void initialize() {
        LOGGER.debug("Requested authentication form...");
        whenPropertyIsSet(serviceInfo, this::serviceInfoIsSet);
        whenPropertyIsSet(formFields, this::formFieldsAreSet);

        setOnClick(submitButton, this::submit);

        hideAndResizeParentIf(submitButton, submitting.not());
        hideAndResizeParentIf(loginProgressIndicator, submitting);
        fieldsBox.disableProperty().bind(submitting);
    }

    private void serviceInfoIsSet() {
        final ThirdpartyServiceInfo serviceInfo = this.serviceInfo.getValue();

        LOGGER.info("Displaying authentication form for service \"{}\"", serviceInfo.getName());

        serviceName.setText(serviceInfo.getName());
    }

    private void formFieldsAreSet() {
        final List<AuthenticationField> authenticationFields = formFields.getValue();

        LOGGER.info("Fields requested: {}", authenticationFields);

        final List<Pane> formFields = authenticationFields
                .stream()
                .map(this::buildAuthenticationField)
                .collect(Collectors.toList());
        fieldsBox.getChildren().setAll(formFields);
    }

    private void submit() {
        submitting.set(true);
        runAsync(
                () -> submitCallbackProp.getValue().accept(fieldValues)
        ).whenCompleteAsync((success, error) -> {
            if (error != null) {
                LOGGER.error("Cannot authenticate!", error);
                submitting.set(false);
            } else {
                LOGGER.info("Authentication succeeded! Closing form stage.");
                formStage.close();
            }
        }, Platform::runLater);
    }

    private Pane buildAuthenticationField(AuthenticationField field) {
        final FxmlLoadResult<Pane, AuthenticationFormFieldController> fieldLoad = easyFxml.loadNode(
                formFieldComponent,
                Pane.class,
                AuthenticationFormFieldController.class
        );
        fieldLoad.afterControllerLoaded(fieldController -> {
            LOGGER.debug("Initializing authentication form field for: {}", field);
            fieldController.setField(field);
            fieldController.setOnFieldUpdated(fieldValue -> fieldValues.put(field, fieldValue));
        });
        return fieldLoad.orExceptionPane().get();
    }

    public void setSubmit(Consumer<Map<AuthenticationField, String>> submit) {
        this.submitCallbackProp.setValue(submit);
    }

    public void setServiceInfo(ThirdpartyServiceInfo serviceInfo) {
        this.serviceInfo.setValue(serviceInfo);
    }

    public void setFormFields(List<AuthenticationField> authenticationFields) {
        this.formFields.setValue(authenticationFields);
    }

    @Override
    public void setOwnStage(Stage stage) {
        this.formStage = stage;
    }

}
