package moe.anitrack.gui.view.views.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import moe.anitrack.core.model.events.authentication.AuthenticatedEvent;
import moe.anitrack.core.model.thirdparties.ThirdpartySelectionService;
import moe.anitrack.gui.view.views.providerselection.ProviderSelectionComponent;
import moe.anitrack.gui.view.views.welcome.WelcomeComponent;
import moe.tristan.easyfxml.EasyFxml;
import moe.tristan.easyfxml.api.FxmlController;
import moe.tristan.easyfxml.api.FxmlNode;

@Component
public class MainController implements FxmlController, ApplicationListener<AuthenticatedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @FXML
    private BorderPane content;

    private final EasyFxml easyFxml;
    private final ProviderSelectionComponent providerSelectionComponent;
    private final WelcomeComponent welcomeComponent;
    private final ThirdpartySelectionService thirdpartySelectionService;

    public MainController(
            EasyFxml easyFxml,
            ProviderSelectionComponent providerSelectionComponent,
            WelcomeComponent welcomeComponent,
            ThirdpartySelectionService thirdpartySelectionService
    ) {
        this.easyFxml = easyFxml;
        this.providerSelectionComponent = providerSelectionComponent;
        this.welcomeComponent = welcomeComponent;
        this.thirdpartySelectionService = thirdpartySelectionService;
    }

    @Override
    public void initialize() {
        displayWelcomeOrSelection();
    }

    private void displayWelcomeOrSelection() {
        var node = thirdpartySelectionService.isCurrentlySet() ? welcomeComponent : providerSelectionComponent;
        displayComponent(node);
    }

    private void displayComponent(FxmlNode component) {
        easyFxml.loadNode(component).orExceptionPane().andThen(content::setCenter);
    }

    @Override
    public void onApplicationEvent(AuthenticatedEvent event) {
        LOGGER.info("Now authenticated with {}! Loading welcome screen.", event.getService().uniqueName());
        Platform.runLater(this::displayWelcomeOrSelection);
    }

}
