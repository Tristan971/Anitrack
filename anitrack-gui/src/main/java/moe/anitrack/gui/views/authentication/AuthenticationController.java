package moe.anitrack.gui.views.authentication;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import moe.anitrack.thirdparties.common.model.authentication.pre.AuthenticationField;
import moe.tristan.easyfxml.api.FxmlController;

@Component
public class AuthenticationController implements FxmlController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @FXML
    private VBox fieldsBox;

    private final Property<List<AuthenticationField>> authenticationFields = new SimpleObjectProperty<>();

    @Override
    public void initialize() {
        LOGGER.info("Authentication form starting up.");

        if (authenticationFields.getValue() == null) {
            authenticationFields.addListener((o, prev, cur) -> loadAuthenticationFields());
        } else {
            loadAuthenticationFields();
        }
    }

    public void setAuthenticationFields(final List<AuthenticationField> authenticationFields) {
        this.authenticationFields.setValue(FXCollections.observableArrayList(authenticationFields));
    }

    private void loadAuthenticationFields() {
        LOGGER.info("Displaying authentication fields : {}", authenticationFields.getValue());
        authenticationFields.getValue().stream().map(this::buildBoxForField).forEachOrdered(fieldsBox.getChildren()::add);
    }

    private HBox buildBoxForField(final AuthenticationField field) {
        final Label label = new Label(field.getFieldName());
        final TextField textField = field.isPasswordLike()
                                    ? new PasswordField()
                                    : new TextField();

        return new HBox(label, textField);
    }

}
