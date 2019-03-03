package moe.anitrack.gui.views.providerselection;

import java.util.List;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import moe.anitrack.gui.views.authentication.AuthenticationFormComponent;
import moe.anitrack.gui.views.authentication.AuthenticationFormController;
import moe.anitrack.gui.views.providerselection.provider.ProviderPanelComponent;
import moe.anitrack.gui.views.providerselection.provider.ProviderPanelController;
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

    public ProviderSelectionController(
            EasyFxml easyFxml,
            ProviderPanelComponent providerPanelComponent,
            List<ThirdpartyService> thirdparties,
            AuthenticationFormComponent authenticationFormComponent
    ) {
        this.easyFxml = easyFxml;
        this.providerPanelComponent = providerPanelComponent;
        this.thirdparties = thirdparties;
        this.authenticationFormComponent = authenticationFormComponent;
    }

    @Override
    public void initialize() {
        thirdparties.stream().map(thirdparty -> {
            final FxmlLoadResult<Pane, ProviderPanelController> load = easyFxml.loadNode(
                    providerPanelComponent,
                    Pane.class,
                    ProviderPanelController.class
            );
            load.afterControllerLoaded(controller -> controller.setPresentedService(thirdparty));
            load.afterNodeLoaded(providerPane -> providerPane.setOnMouseClicked(clickEvent -> {
                final FxmlLoadResult<Pane, AuthenticationFormController> providerAuthenticationForm = easyFxml.loadNode(
                        authenticationFormComponent,
                        Pane.class,
                        AuthenticationFormController.class
                );
                providerAuthenticationForm.afterControllerLoaded(formController -> formController.setServiceRequested(thirdparty));
                Stage formStage = new Stage();
                formStage.setTitle(thirdparty.getChoiceInfo().getName());
                formStage.setScene(new Scene(providerAuthenticationForm.orExceptionPane().get()));
                formStage.show();
            }));
            return load.getNode().getOrElseGet(ExceptionHandler::fromThrowable);
        }).forEach(providerPanels.getChildren()::add);
    }

}
