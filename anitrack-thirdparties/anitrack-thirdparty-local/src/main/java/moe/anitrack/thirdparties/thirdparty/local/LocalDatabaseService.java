package moe.anitrack.thirdparties.thirdparty.local;

import org.springframework.stereotype.Component;

import moe.anitrack.thirdparties.common.ThirdpartyAuthenticationService;
import moe.anitrack.thirdparties.common.ThirdpartyService;
import moe.anitrack.thirdparties.common.ThirdpartyTracker;
import moe.anitrack.thirdparties.common.model.presentation.ThirdpartyInfo;

@Component
public class LocalDatabaseService implements ThirdpartyService {

    private final LocalDatabaseTracker localDatabaseTracker;
    private final LocalDatabaseAuthenticationService localDatabaseAuthenticationService;

    public LocalDatabaseService(LocalDatabaseTracker localDatabaseTracker, LocalDatabaseAuthenticationService localDatabaseAuthenticationService) {
        this.localDatabaseTracker = localDatabaseTracker;
        this.localDatabaseAuthenticationService = localDatabaseAuthenticationService;
    }

    @Override
    public ThirdpartyInfo getInfo() {
        return ThirdpartyInfo.of("Local database", getClass().getClassLoader().getResource("local.png"));
    }

    @Override
    public ThirdpartyTracker getTracker() {
        return localDatabaseTracker;
    }

    @Override
    public ThirdpartyAuthenticationService getAuthenticationService() {
        return localDatabaseAuthenticationService;
    }

}
