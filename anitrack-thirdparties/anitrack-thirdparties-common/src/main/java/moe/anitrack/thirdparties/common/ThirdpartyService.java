package moe.anitrack.thirdparties.common;

import moe.anitrack.thirdparties.common.model.presentation.ThirdpartyInfo;

public interface ThirdpartyService {

    ThirdpartyInfo getInfo();

    ThirdpartyTracker getTracker();

    ThirdpartyAuthenticationService getAuthenticationService();

}
