package moe.anitrack.gui.view.views.welcome;

import static moe.tristan.easyfxml.util.Properties.whenPropertyIsSet;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import moe.anitrack.core.model.events.authentication.AuthenticatedEvent;
import moe.anitrack.thirdparties.common.ThirdpartyService;
import moe.anitrack.thirdparties.common.model.presentation.ThirdpartyServiceInfo;
import moe.tristan.easyfxml.api.FxmlController;

@Component
public class WelcomeController implements FxmlController {

    @FXML
    private Label providerName;

    @FXML
    private ImageView providerLogo;

    private final Property<ThirdpartyService> currentServiceProp = new SimpleObjectProperty<>();

    @Override
    public void initialize() {
        whenPropertyIsSet(currentServiceProp, this::displayService);
    }

    @EventListener(AuthenticatedEvent.class)
    public void updateCurrentService(AuthenticatedEvent authenticatedEvent) {
        currentServiceProp.setValue(authenticatedEvent.getService());
    }

    private void displayService(ThirdpartyService service) {
        final ThirdpartyServiceInfo choiceInfo = service.getChoiceInfo();
        providerName.setText(choiceInfo.getName());
        providerLogo.setImage(new Image(choiceInfo.getLogoUrl().toString()));
    }

}
