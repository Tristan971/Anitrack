package moe.anitrack.thirdparties.thirdparty.kitsu;

import org.springframework.stereotype.Component;

import moe.anitrack.thirdparties.common.ThirdpartyAuthenticationService;
import moe.anitrack.thirdparties.common.ThirdpartyService;
import moe.anitrack.thirdparties.common.ThirdpartyTracker;
import moe.anitrack.thirdparties.common.model.presentation.ThirdpartyInfo;
import moe.anitrack.thirdparties.thirdparty.kitsu.auth.KitsuAuthenticationService;

@Component
public class KitsuService implements ThirdpartyService {

    private final KitsuTracker kitsuTracker;
    private final KitsuAuthenticationService kitsuAuthenticationService;

    public KitsuService(KitsuTracker kitsuTracker, KitsuAuthenticationService kitsuAuthenticationService) {
        this.kitsuTracker = kitsuTracker;
        this.kitsuAuthenticationService = kitsuAuthenticationService;
    }

    @Override
    public ThirdpartyInfo getInfo() {
        return ThirdpartyInfo
                .builder()
                .name("Kitsu")
                .logo(getClass().getClassLoader().getResource("kitsu-logo.jpg"))
                .build();
    }

    @Override
    public ThirdpartyTracker getTracker() {
        return kitsuTracker;
    }

    @Override
    public ThirdpartyAuthenticationService getAuthenticationService() {
        return kitsuAuthenticationService;
    }

}
