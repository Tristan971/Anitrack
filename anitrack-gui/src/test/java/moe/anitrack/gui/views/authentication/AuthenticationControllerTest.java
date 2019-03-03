package moe.anitrack.gui.views.authentication;

import static moe.anitrack.gui.views.Components.AUTH_FORM;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import moe.anitrack.thirdparties.common.model.authentication.pre.AuthenticationField;
import moe.tristan.easyfxml.EasyFxml;
import moe.tristan.easyfxml.model.fxml.FxmlLoadResult;
import moe.tristan.easyfxml.test.FxNodeTest;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthenticationControllerTest extends FxNodeTest {

    private static final String FIELD_SIMPLE = "field_simple";
    private static final String FIELD_PASSWORD = "field_password";
    private static final List<AuthenticationField> AUTHENTICATION_FIELDS = List.of(
            AuthenticationField.of(FIELD_SIMPLE, false),
            AuthenticationField.of(FIELD_PASSWORD, true)
    );

    @Autowired
    private EasyFxml easyFxml;

    private Pane component;

    @Before
    public void setUp() {
        final FxmlLoadResult<Pane, AuthenticationController> loadResult = easyFxml.loadNode(AUTH_FORM, Pane.class, AuthenticationController.class);
        loadResult.afterControllerLoaded(authController -> authController.setAuthenticationFields(AUTHENTICATION_FIELDS));
        component = loadResult.getNode().getOrNull();
    }

    @Test
    public void loadsTwoFields() {
        withNodes(component).andAwaitFor(() -> lookup(".text-field").query().isVisible());

        final TextField simpleField = lookup(".text-field").queryAs(TextField.class);
        assertThat(simpleField).isNotNull();
        final PasswordField passwordField = lookup(".password-field").queryAs(PasswordField.class);
        assertThat(passwordField).isNotNull();
    }

}
