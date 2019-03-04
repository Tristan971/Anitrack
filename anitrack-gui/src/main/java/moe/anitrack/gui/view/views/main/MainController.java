package moe.anitrack.gui.view.views.main;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import moe.anitrack.core.model.thirdparties.ThirdpartySelectionService;
import moe.tristan.easyfxml.api.FxmlController;

@Component
public class MainController implements FxmlController {

    @FXML
    private BorderPane content;

    private final ThirdpartySelectionService thirdpartySelectionService;

    public MainController(ThirdpartySelectionService thirdpartySelectionService) {
        this.thirdpartySelectionService = thirdpartySelectionService;
    }

    @Override
    public void initialize() {

    }

}
