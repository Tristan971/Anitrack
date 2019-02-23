package moe.anitrack.gui.views.providerselection;

import static moe.tristan.easyfxml.util.Properties.whenPropertyIsSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import moe.anitrack.thirdparties.common.ThirdpartyService;
import moe.anitrack.thirdparties.common.model.presentation.ThirdpartyServiceChoiceInfo;
import moe.tristan.easyfxml.api.FxmlController;

@Component
public class ProviderSelectPanelController implements FxmlController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderSelectPanelController.class);

    @FXML
    private ImageView logo;

    @FXML
    private Label name;

    private final Property<ThirdpartyService> presentedService = new SimpleObjectProperty<>();

    @Override
    public void initialize() {
        whenPropertyIsSet(presentedService, this::servicePresentedSet);
    }

    private void servicePresentedSet(ThirdpartyService value) {
        final ThirdpartyServiceChoiceInfo choiceInfo = value.getChoiceInfo();
        Platform.runLater(() -> {
            LOGGER.info("Loaded third-party service provider pane for {}", value.getClass().getSimpleName());
            logo.setImage(new Image(choiceInfo.getLogoUrl().toString()));
            name.setText(choiceInfo.getName());
        });
    }

    public void setPresentedService(ThirdpartyService presentedService) {
        this.presentedService.setValue(presentedService);
    }

}
