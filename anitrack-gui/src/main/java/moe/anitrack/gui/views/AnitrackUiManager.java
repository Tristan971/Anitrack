package moe.anitrack.gui.views;

import static moe.anitrack.gui.views.Components.PROVIDER_SELECTION;
import static moe.anitrack.gui.views.Components.ROOT;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import moe.anitrack.core.thirdparties.choice.ThirdpartySelectionService;
import moe.tristan.easyfxml.FxUiManager;
import moe.tristan.easyfxml.api.FxmlNode;

@Component
public class AnitrackUiManager extends FxUiManager {

    private final Environment environment;
    private final ThirdpartySelectionService thirdpartySelectionService;

    protected AnitrackUiManager(Environment environment, ThirdpartySelectionService thirdpartySelectionService) {
        this.environment = environment;
        this.thirdpartySelectionService = thirdpartySelectionService;
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
        return thirdpartySelectionService.isCurrentlySet() ? ROOT : PROVIDER_SELECTION;
    }

}
