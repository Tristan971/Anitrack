package moe.anitrack.gui.views.authentication;

import static javafx.collections.FXCollections.observableList;
import static moe.tristan.easyfxml.util.Buttons.setOnClick;
import static moe.tristan.easyfxml.util.Properties.whenPropertyIsSet;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import java.util.Map;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;

import moe.anitrack.gui.views.authentication.field.AuthenticationFormFieldCell;
import moe.anitrack.thirdparties.common.ThirdpartyService;
import moe.anitrack.thirdparties.common.model.authentication.pre.AuthenticationField;
import moe.tristan.easyfxml.model.components.listview.ComponentListViewFxmlController;

@Component
@Scope(scopeName = SCOPE_PROTOTYPE)
public class AuthenticationFormController extends ComponentListViewFxmlController<AuthenticationField> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFormController.class);

    @FXML
    private Label serviceName;

    @FXML
    private VBox fieldsBox;

    @FXML
    public Button submitButton;

    @FXML
    private ProgressIndicator loginProgressIndicator;

    private final Property<ThirdpartyService> serviceRequestedProp = new SimpleObjectProperty<>();
    private final Property<Consumer<Map<String, String>>> fieldsValuesCallbackProperty = new SimpleObjectProperty<>();

    public AuthenticationFormController(ApplicationContext applicationContext) {
        super(applicationContext, AuthenticationFormFieldCell.class);
    }

    @Override
    public void initialize() {
        LOGGER.debug("Requested authentication form...");
        whenPropertyIsSet(serviceRequestedProp, this::serviceIsSet);
        whenPropertyIsSet(fieldsValuesCallbackProperty, () -> setOnClick(submitButton, this::submit));
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
        listView.setItems(observableList(service.getAuthenticationService().getAuthenticationFields()));
    }

    private void submit() {
        LOGGER.info("Tried submitting");
    }

}
