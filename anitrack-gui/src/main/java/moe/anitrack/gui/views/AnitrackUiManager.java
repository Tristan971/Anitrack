package moe.anitrack.gui.views;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import moe.tristan.easyfxml.EasyFxml;
import moe.tristan.easyfxml.FxUiManager;
import moe.tristan.easyfxml.api.FxmlNode;

@Component
public class AnitrackUiManager extends FxUiManager {

    private final Environment environment;

    protected AnitrackUiManager(EasyFxml easyFxml, Environment environment) {
        super(easyFxml);
        this.environment = environment;
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
        return Components.ROOT;
    }

}
