package moe.anitrack.gui.view.views.main;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import moe.anitrack.core.model.thirdparties.ThirdpartySelectionService;
import moe.anitrack.gui.view.views.providerselection.ProviderSelectionComponent;
import moe.tristan.easyfxml.EasyFxml;
import moe.tristan.easyfxml.api.FxmlController;

@Component
public class MainController implements FxmlController {

    @FXML
    private BorderPane content;

    private final EasyFxml easyFxml;
    private final ProviderSelectionComponent providerSelectionComponent;
    private final ThirdpartySelectionService thirdpartySelectionService;

    public MainController(
            EasyFxml easyFxml,
            ProviderSelectionComponent providerSelectionComponent,
            ThirdpartySelectionService thirdpartySelectionService
    ) {
        this.easyFxml = easyFxml;
        this.providerSelectionComponent = providerSelectionComponent;
        this.thirdpartySelectionService = thirdpartySelectionService;
    }

    @Override
    public void initialize() {
        if (!thirdpartySelectionService.isCurrentlySet()) {
            requestThirdpartySelection();
        } else {
            content.setCenter(new Label("Good to go"));
        }
    }

    private void requestThirdpartySelection() {
        easyFxml.loadNode(providerSelectionComponent).orExceptionPane().andThen(content::setCenter);
    }

}
