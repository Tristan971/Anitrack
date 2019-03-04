package moe.anitrack.gui.operations;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import moe.anitrack.gui.view.views.authentication.AuthenticationFormComponent;
import moe.anitrack.gui.view.views.authentication.AuthenticationFormController;
import moe.anitrack.thirdparties.common.ThirdpartyService;
import moe.anitrack.thirdparties.common.model.authentication.AuthenticationResult;
import moe.tristan.easyfxml.EasyFxml;
import moe.tristan.easyfxml.model.fxml.FxmlLoadResult;

@Component
public class AuthenticateWithThirdpartyOperation {

    private final EasyFxml easyFxml;
    private final AuthenticationFormComponent formComponent;

    public AuthenticateWithThirdpartyOperation(EasyFxml easyFxml, AuthenticationFormComponent formComponent) {
        this.easyFxml = easyFxml;
        this.formComponent = formComponent;
    }

    public CompletableFuture<AuthenticationResult> authenticateWithFormFor(ThirdpartyService service) {
        CompletableFuture<AuthenticationResult> result = new CompletableFuture<>();
        Platform.runLater(() -> openAuthenticationFormForService(service, result));
        return result;
    }

    private void openAuthenticationFormForService(ThirdpartyService service, CompletableFuture<AuthenticationResult> setWhenValidated) {
        final FxmlLoadResult<Pane, AuthenticationFormController> form = easyFxml.loadNode(
                formComponent,
                Pane.class,
                AuthenticationFormController.class
        );

        Stage formStage = new Stage();
        formStage.setTitle("Authenticate with " + service.getChoiceInfo().getName());

        form.afterControllerLoaded(formController -> {
            formController.setServiceInfo(service.getChoiceInfo());
            formController.setFormFields(service.getAuthenticationService().getAuthenticationFields());
            formController.setOwnStage(formStage);
            formController.setSubmit(values -> {
                final AuthenticationResult authResult = service.getAuthenticationService().authenticateWith(values);
                setWhenValidated.complete(authResult);
            });
        });

        formStage.setScene(new Scene(form.orExceptionPane().get()));
        formStage.sizeToScene();
        formStage.show();
    }

}
