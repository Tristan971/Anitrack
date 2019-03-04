package moe.anitrack.gui.view.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javafx.stage.Stage;

import moe.anitrack.gui.view.views.main.MainComponent;
import moe.tristan.easyfxml.FxUiManager;
import moe.tristan.easyfxml.api.FxmlNode;

@Component
public class AnitrackUiManager extends FxUiManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnitrackUiManager.class);

    private final Environment environment;
    private final MainComponent mainComponent;

    protected AnitrackUiManager(Environment environment, MainComponent mainComponent) {
        this.environment = environment;
        this.mainComponent = mainComponent;
    }

    @Override
    public void startGui(Stage mainStage) {
        super.startGui(mainStage);
        mainStage.sizeToScene();
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
        return mainComponent;
    }

    @Override
    protected void onStageCreated(Stage mainStage) {
        mainStage.setOnCloseRequest(e -> {
            LOGGER.info("Will exit application!");
            System.exit(0);
        });
    }

}
