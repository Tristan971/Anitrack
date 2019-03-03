package moe.anitrack.gui.views.providerselection;

import java.util.List;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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

    public ProviderSelectionController(
            EasyFxml easyFxml,
            ProviderPanelComponent providerPanelComponent,
            List<ThirdpartyService> thirdparties
    ) {
        this.easyFxml = easyFxml;
        this.providerPanelComponent = providerPanelComponent;
        this.thirdparties = thirdparties;
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
            return load.getNode().getOrElseGet(ExceptionHandler::fromThrowable);
        }).forEach(providerPanels.getChildren()::add);
    }

}
