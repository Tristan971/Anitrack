package moe.anitrack.gui.views.providerselection;

import static moe.anitrack.gui.views.Components.PROVIDER_SELECTION_PANE;

import java.util.List;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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

    public ProviderSelectionController(EasyFxml easyFxml, List<ThirdpartyService> thirdparties) {
        this.easyFxml = easyFxml;
        this.thirdparties = thirdparties;
    }

    @Override
    public void initialize() {
        thirdparties.stream().map(thirdparty -> {
            final FxmlLoadResult<Pane, ProviderSelectPanelController> load = easyFxml.loadNode(
                    PROVIDER_SELECTION_PANE,
                    Pane.class,
                    ProviderSelectPanelController.class
            );
            load.afterControllerLoaded(controller -> controller.setPresentedService(thirdparty));
            return load.getNode().getOrElseGet(ExceptionHandler::fromThrowable);
        }).forEach(providerPanels.getChildren()::add);
    }

}
