package moe.anitrack.gui.view.views.authentication;

import static moe.tristan.easyfxml.util.Buttons.setOnClick;
import static moe.tristan.easyfxml.util.Nodes.hideAndResizeParentIf;
import static moe.tristan.easyfxml.util.Properties.whenPropertyIsSet;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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

import moe.anitrack.gui.view.views.authentication.field.AuthenticationFormFieldComponent;
import moe.anitrack.gui.view.views.authentication.field.AuthenticationFormFieldController;
import moe.anitrack.thirdparties.common.ThirdpartyService;
import moe.anitrack.thirdparties.common.model.authentication.pre.AuthenticationField;
import moe.tristan.easyfxml.EasyFxml;
import moe.tristan.easyfxml.api.FxmlController;
import moe.tristan.easyfxml.model.fxml.FxmlLoadResult;

@Component
@Scope(scopeName = SCOPE_PROTOTYPE)
public class AuthenticationFormController implements FxmlController {

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

    private final Property<ThirdpartyService> serviceRequestedProp = new SimpleObjectProperty<>();

    private final BooleanProperty submitting = new SimpleBooleanProperty(false);
    private final Map<String, String> submittedValues = new HashMap<>();

    public AuthenticationFormController(EasyFxml easyFxml, AuthenticationFormFieldComponent formFieldComponent) {
        this.easyFxml = easyFxml;
        this.formFieldComponent = formFieldComponent;
    }

    @Override
    public void initialize() {
        LOGGER.debug("Requested authentication form...");
        whenPropertyIsSet(serviceRequestedProp, this::serviceIsSet);
        setOnClick(submitButton, this::submit);

        hideAndResizeParentIf(loginProgressIndicator, submitting);
    }

    public void setServiceRequested(ThirdpartyService serviceRequested) {
        this.serviceRequestedProp.setValue(serviceRequested);
    }

    private void serviceIsSet() {
        final ThirdpartyService service = serviceRequestedProp.getValue();

        LOGGER.info(
                "Displaying authentication form for service \"{}\": {}",
                service.getChoiceInfo().getName(),
                service.getAuthenticationService().getAuthenticationFields()
        );

        serviceName.setText(service.getChoiceInfo().getName());
        final List<Pane> fields = buildAuthenticationFields(service);
        fieldsBox.getChildren().setAll(fields);
    }

    private void submit() {
        CompletableFuture.runAsync(
                () -> serviceRequestedProp.getValue().getAuthenticationService().authenticateWith(submittedValues)
        ).whenCompleteAsync((success, error) -> {
            if (error != null) {
                LOGGER.error("Cannot authenticate!", error);
                submitting.set(false);
            } else {

            }
        });
    }

    private List<Pane> buildAuthenticationFields(ThirdpartyService service) {
        return service
                .getAuthenticationService()
                .getAuthenticationFields()
                .stream()
                .map(this::buildAuthenticationField)
                .collect(Collectors.toList());
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
            fieldController.setOnFieldUpdated(fieldValue -> submittedValues.put(field.getFieldName(), fieldValue));
        });
        return fieldLoad.orExceptionPane().get();
    }

}
