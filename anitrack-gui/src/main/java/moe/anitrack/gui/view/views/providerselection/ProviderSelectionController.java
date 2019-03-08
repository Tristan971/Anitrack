package moe.anitrack.gui.view.views.providerselection;

import java.util.List;

import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import moe.anitrack.core.model.events.authentication.AuthenticatedEvent;
import moe.anitrack.gui.view.views.authentication.AuthenticationFormComponent;
import moe.anitrack.gui.view.views.authentication.AuthenticationFormController;
import moe.anitrack.gui.view.views.providerselection.provider.ProviderPanelComponent;
import moe.anitrack.gui.view.views.providerselection.provider.ProviderPanelController;
import moe.anitrack.thirdparties.common.ThirdpartyService;
import moe.anitrack.thirdparties.common.model.authentication.AuthenticationResult;
import moe.tristan.easyfxml.EasyFxml;
import moe.tristan.easyfxml.api.FxmlController;
import moe.tristan.easyfxml.model.exception.ExceptionHandler;
import moe.tristan.easyfxml.model.fxml.FxmlLoadResult;

@Component
public class ProviderSelectionController implements FxmlController {

    @FXML
    private VBox providerPanels;

    private final EasyFxml easyFxml;
    private final List<ThirdpartyService> thirdparties;
    private final ProviderPanelComponent providerPanelComponent;
    private final AuthenticationFormComponent authenticationFormComponent;
    private final ApplicationEventMulticaster eventMulticaster;

    public ProviderSelectionController(
            EasyFxml easyFxml,
            List<ThirdpartyService> thirdparties,
            ProviderPanelComponent providerPanelComponent,
            AuthenticationFormComponent authenticationFormComponent,
            ApplicationEventMulticaster eventMulticaster
    ) {
        this.easyFxml = easyFxml;
        this.thirdparties = thirdparties;
        this.providerPanelComponent = providerPanelComponent;
        this.authenticationFormComponent = authenticationFormComponent;
        this.eventMulticaster = eventMulticaster;
    }

    @Override
    public void initialize() {
        thirdparties.stream().map(this::buildProviderPane).forEach(providerPanels.getChildren()::add);
    }

    private Pane buildProviderPane(ThirdpartyService thirdparty) {
        final FxmlLoadResult<Pane, ProviderPanelController> load = easyFxml.loadNode(
                providerPanelComponent,
                Pane.class,
                ProviderPanelController.class
        );

        load.afterControllerLoaded(controller -> controller.setPresentedService(thirdparty));
        load.afterNodeLoaded(providerPane -> providerPane.setOnMouseClicked(
                clickEvent -> openAuthenticationFormForService(thirdparty)
        ));

        return load.getNode().getOrElseGet(ExceptionHandler::fromThrowable);
    }

    private void openAuthenticationFormForService(ThirdpartyService service) {
        final FxmlLoadResult<Pane, AuthenticationFormController> form = easyFxml.loadNode(
                authenticationFormComponent,
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
                eventMulticaster.multicastEvent(new AuthenticatedEvent(this, service, authResult));
            });
            providerPanels.disableProperty().bind(formStage.showingProperty());
        });

        formStage.setScene(new Scene(form.orExceptionPane().get()));
        formStage.sizeToScene();
        formStage.show();
    }

}
