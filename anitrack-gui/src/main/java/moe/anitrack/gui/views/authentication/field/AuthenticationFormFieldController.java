package moe.anitrack.gui.views.authentication.field;

import static moe.tristan.easyfxml.util.Properties.whenPropertyIsSet;

import java.util.function.Supplier;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import moe.anitrack.thirdparties.common.model.authentication.pre.AuthenticationField;
import moe.tristan.easyfxml.model.components.listview.ComponentCellFxmlController;

public class AuthenticationFormFieldController implements ComponentCellFxmlController<AuthenticationField> {

    @FXML
    private Label fieldName;
    @FXML
    private HBox fieldBox;

    private Property<AuthenticationField> fieldProperty = new SimpleObjectProperty<>();

    @Override
    public void initialize() {
        whenPropertyIsSet(fieldProperty, this::displayField);
    }

    @Override
    public void updateWithValue(AuthenticationField newValue) {
        this.fieldProperty.setValue(newValue);
    }

    private void displayField() {
        final AuthenticationField field = fieldProperty.getValue();
        fieldName.setText(field.getFieldName());

        Supplier<Node> typeOfFieldToAdd = field.isPasswordLike() ? PasswordField::new : TextField::new;
        fieldBox.getChildren().setAll(typeOfFieldToAdd.get());
    }

}
