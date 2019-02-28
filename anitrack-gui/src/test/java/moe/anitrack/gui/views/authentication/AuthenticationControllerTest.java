package moe.anitrack.gui.views.authentication;

import static moe.anitrack.gui.views.Components.AUTH_FORM;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testfx.framework.junit.ApplicationTest;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import moe.anitrack.thirdparties.common.model.authentication.pre.AuthenticationField;
import moe.anitrack.thirdparties.common.model.authentication.pre.ImmutableAuthenticationField;
import moe.tristan.easyfxml.EasyFxml;
import moe.tristan.easyfxml.model.fxml.FxmlLoadResult;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthenticationControllerTest extends ApplicationTest {

    private static final String FIELD_SIMPLE = "field_simple";
    private static final String FIELD_PASSWORD = "field_password";

    @Autowired
    private EasyFxml easyFxml;

    private final List<AuthenticationField> authenticationFields = List.of(
            ImmutableAuthenticationField.of(FIELD_SIMPLE, false),
            ImmutableAuthenticationField.of(FIELD_PASSWORD, true)
    );

    @Override
    public void start(Stage stage) {
        Platform.runLater(() -> {
            final FxmlLoadResult<Pane, AuthenticationController> loadResult = easyFxml.loadNode(AUTH_FORM, Pane.class, AuthenticationController.class);
            loadResult.afterControllerLoaded(authController -> authController.setAuthenticationFields(authenticationFields));
            final Pane node = loadResult.getNode().getOrNull();
            stage.setScene(new Scene(node));
            stage.show();
        });
    }

    @Test
    public void loadsTwoFields() {
        Platform.runLater(() -> {
            final TextField simpleField = lookup(".text-field").queryAs(TextField.class);
            assertThat(simpleField).isNotNull();
            final PasswordField passwordField = lookup(".password-field").queryAs(PasswordField.class);
            assertThat(passwordField).isNotNull();
        });
    }

}
