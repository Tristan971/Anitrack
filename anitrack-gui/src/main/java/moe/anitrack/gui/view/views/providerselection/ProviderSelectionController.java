package moe.anitrack.gui.view.views.providerselection;

import java.util.List;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import moe.anitrack.gui.operations.AuthenticateWithThirdpartyOperation;
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
    private final List<ThirdpartyService> thirdparties;
    private final ProviderPanelComponent providerPanelComponent;
    private final AuthenticateWithThirdpartyOperation authenticateWithThirdpartyOperation;

    public ProviderSelectionController(
            EasyFxml easyFxml,
            List<ThirdpartyService> thirdparties,
            ProviderPanelComponent providerPanelComponent,
            AuthenticateWithThirdpartyOperation authenticateWithThirdpartyOperation
    ) {
        this.easyFxml = easyFxml;
        this.thirdparties = thirdparties;
        this.providerPanelComponent = providerPanelComponent;
        this.authenticateWithThirdpartyOperation = authenticateWithThirdpartyOperation;
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
                clickEvent -> authenticateWithThirdpartyOperation.authenticateWithFormFor(thirdparty)
        ));

        return load.getNode().getOrElseGet(ExceptionHandler::fromThrowable);
    }

}
