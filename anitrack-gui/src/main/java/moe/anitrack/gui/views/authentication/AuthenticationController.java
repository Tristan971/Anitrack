package moe.anitrack.gui.views.authentication;

import java.util.List;

import org.springframework.stereotype.Component;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
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

    @FXML
    private VBox fieldsBox;

    private final ListProperty<AuthenticationField> authenticationFields = new SimpleListProperty<>();

    @Override
    public void initialize() {
        authenticationFields.stream().map(this::buildBoxForField).forEachOrdered(fieldsBox.getChildren()::add);
    }

    public void setAuthenticationFields(final List<AuthenticationField> authenticationFields) {
        this.authenticationFields.setValue(FXCollections.observableArrayList(authenticationFields));
    }

    private HBox buildBoxForField(final AuthenticationField field) {
        final Label label = new Label(field.getFieldName());
        final TextField textField = field.isPasswordLike()
                                    ? new PasswordField()
                                    : new TextField();

        return new HBox(label, textField);
    }

}
