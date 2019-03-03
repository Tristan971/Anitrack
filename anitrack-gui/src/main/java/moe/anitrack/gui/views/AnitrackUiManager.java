package moe.anitrack.gui.views;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import moe.anitrack.core.thirdparties.choice.ThirdpartySelectionService;
import moe.anitrack.gui.views.providerselection.ProviderSelectionComponent;
import moe.tristan.easyfxml.FxUiManager;
import moe.tristan.easyfxml.api.FxmlNode;

@Component
public class AnitrackUiManager extends FxUiManager {

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

}
