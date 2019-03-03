package moe.anitrack.gui.view.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javafx.stage.Stage;

import moe.anitrack.core.thirdparties.choice.ThirdpartySelectionService;
import moe.anitrack.gui.view.views.providerselection.ProviderSelectionComponent;
import moe.tristan.easyfxml.FxUiManager;
import moe.tristan.easyfxml.api.FxmlNode;

@Component
public class AnitrackUiManager extends FxUiManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnitrackUiManager.class);

    private final Environment environment;
    private final ThirdpartySelectionService thirdpartySelectionService;

    private final ProviderSelectionComponent providerSelectionComponent;

    protected AnitrackUiManager(
            Environment environment,
            ThirdpartySelectionService thirdpartySelectionService,
            ProviderSelectionComponent providerSelectionComponent
    ) {
        this.environment = environment;
        this.thirdpartySelectionService = thirdpartySelectionService;
        this.providerSelectionComponent = providerSelectionComponent;
    }

    @Override
    protected String title() {
        return String.format(
                "%s %s",
                environment.getRequiredProperty("app.name"),
                environment.getRequiredProperty("app.version")
        );
    }

    @Override
    protected FxmlNode mainComponent() {
        if (thirdpartySelectionService.isCurrentlySet()) {
            return null;
        }
        return providerSelectionComponent;
    }

    @Override
    protected void onStageCreated(Stage mainStage) {
        mainStage.setOnCloseRequest(e -> {
            LOGGER.info("Will exit application!");
            System.exit(0);
        });
    }

}
