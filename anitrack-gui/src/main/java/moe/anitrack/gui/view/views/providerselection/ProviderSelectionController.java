package moe.anitrack.gui.view.views.providerselection;

import java.util.List;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import moe.anitrack.core.thirdparties.choice.ThirdpartySelectionService;
import moe.anitrack.gui.view.views.authentication.AuthenticationFormComponent;
import moe.anitrack.gui.view.views.authentication.AuthenticationFormController;
import moe.anitrack.gui.view.views.providerselection.provider.ProviderPanelComponent;
import moe.anitrack.gui.view.views.providerselection.provider.ProviderPanelController;
import moe.anitrack.thirdparties.common.ThirdpartyService;
import moe.tristan.easyfxml.EasyFxml;
import moe.tristan.easyfxml.api.FxmlController;
import moe.tristan.easyfxml.model.exception.ExceptionHandler;
import moe.tristan.easyfxml.model.fxml.FxmlLoadResult;

@Component
public class ProviderSelectionController implements FxmlController {

    @FXML
    private VBox providerPanels;

    private final EasyFxml easyFxml;
    private final ProviderPanelComponent providerPanelComponent;
    private final List<ThirdpartyService> thirdparties;
    private final AuthenticationFormComponent authenticationFormComponent;
    private final ThirdpartySelectionService thirdpartySelectionService;

    public ProviderSelectionController(
            EasyFxml easyFxml,
            ProviderPanelComponent providerPanelComponent,
            List<ThirdpartyService> thirdparties,
            AuthenticationFormComponent authenticationFormComponent,
            ThirdpartySelectionService thirdpartySelectionService
    ) {
        this.easyFxml = easyFxml;
        this.providerPanelComponent = providerPanelComponent;
        this.thirdparties = thirdparties;
        this.authenticationFormComponent = authenticationFormComponent;
        this.thirdpartySelectionService = thirdpartySelectionService;
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
        load.afterNodeLoaded(providerPane -> providerPane.setOnMouseClicked(clickEvent -> openServiceAuthenticationWindow(thirdparty)));
        return load.getNode().getOrElseGet(ExceptionHandler::fromThrowable);
    }

    private void openServiceAuthenticationWindow(ThirdpartyService thirdparty) {
        final FxmlLoadResult<Pane, AuthenticationFormController> providerAuthenticationForm = easyFxml.loadNode(
                authenticationFormComponent,
                Pane.class,
                AuthenticationFormController.class
        );
        Stage formStage = new Stage();
        providerAuthenticationForm.afterControllerLoaded(formController -> {
            formController.setServiceInfo(thirdparty.getChoiceInfo());
            formController.setFormFields(thirdparty.getAuthenticationService().getAuthenticationFields());
            formController.setOwnStage(formStage);
        });
        formStage.setTitle(thirdparty.getChoiceInfo().getName());
        formStage.setScene(new Scene(providerAuthenticationForm.orExceptionPane().get()));
        formStage.sizeToScene();
        formStage.show();
        formStage.toFront();
    }

}
