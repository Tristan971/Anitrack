package moe.anitrack.gui.view.views.authentication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

import moe.anitrack.thirdparties.common.ThirdpartyAuthenticationService;
import moe.anitrack.thirdparties.common.ThirdpartyService;
import moe.anitrack.thirdparties.common.model.authentication.pre.AuthenticationField;
import moe.anitrack.thirdparties.common.model.presentation.ThirdpartyServiceInfo;
import moe.tristan.easyfxml.EasyFxml;
import moe.tristan.easyfxml.model.fxml.FxmlLoadResult;
import moe.tristan.easyfxml.test.FxNodeTest;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthenticationFormControllerTest extends FxNodeTest {

    private static final String SERVICE_NAME = "Mock Service";

    private static final String FIELD_SIMPLE = "field_simple";
    private static final String FIELD_PASSWORD = "field_password";
    private static final List<AuthenticationField> AUTHENTICATION_FIELDS = List.of(
            AuthenticationField.of(FIELD_SIMPLE, false),
            AuthenticationField.of(FIELD_PASSWORD, true)
    );

    @Autowired
    private EasyFxml easyFxml;
    @Autowired
    private AuthenticationFormComponent formComponent;

    private ThirdpartyService service;

    private Pane component;

    @Before
    public void setUp() {
        service = mock(ThirdpartyService.class);
        final var authenticationService = mock(ThirdpartyAuthenticationService.class);
        when(service.getAuthenticationService()).thenReturn(authenticationService);
        when(authenticationService.getAuthenticationFields()).thenReturn(AUTHENTICATION_FIELDS);

        final var choiceInfo = mock(ThirdpartyServiceInfo.class);
        when(service.getChoiceInfo()).thenReturn(choiceInfo);
        when(choiceInfo.getName()).thenReturn(SERVICE_NAME);

        final FxmlLoadResult<Pane, AuthenticationFormController> loadResult = easyFxml.loadNode(formComponent, Pane.class, AuthenticationFormController.class);
        loadResult.afterControllerLoaded(authController -> authController.setServiceRequested(service));
        component = loadResult.getNode().getOrNull();
    }

    @Test
    public void loadsFieldsAndSetsServiceName() {
        withNodes(component).andAwaitFor(() -> component.isVisible());

        final TextField simpleField = lookup(".text-field").queryAs(TextField.class);
        assertThat(simpleField).isNotNull();
        final PasswordField passwordField = lookup(".password-field").queryAs(PasswordField.class);
        assertThat(passwordField).isNotNull();
    }

}
