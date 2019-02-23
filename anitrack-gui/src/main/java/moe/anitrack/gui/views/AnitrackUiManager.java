package moe.anitrack.gui.views;

import static moe.anitrack.gui.views.Components.PROVIDER_SELECTION;
import static moe.anitrack.gui.views.Components.ROOT;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import moe.anitrack.core.thirdparties.CurrentThirdpartySystem;
import moe.tristan.easyfxml.EasyFxml;
import moe.tristan.easyfxml.FxUiManager;
import moe.tristan.easyfxml.api.FxmlNode;

@Component
public class AnitrackUiManager extends FxUiManager {

    private final Environment environment;
    private final CurrentThirdpartySystem currentThirdpartySystem;

    protected AnitrackUiManager(EasyFxml easyFxml, Environment environment, CurrentThirdpartySystem currentThirdpartySystem) {
        super(easyFxml);
        this.environment = environment;
        this.currentThirdpartySystem = currentThirdpartySystem;
    }

    @Override
    protected String title() {
        return String.format(
                "%s %s",
                environment.getProperty("app.title"),
                environment.getProperty("app.version")
        );
    }

    @Override
    protected FxmlNode mainComponent() {
        return currentThirdpartySystem.isCurrentlySet() ? ROOT : PROVIDER_SELECTION;
    }

}
