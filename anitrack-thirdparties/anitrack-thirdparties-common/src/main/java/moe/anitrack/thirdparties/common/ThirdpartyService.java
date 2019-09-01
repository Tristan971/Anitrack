package moe.anitrack.thirdparties.common;

import moe.anitrack.thirdparties.common.model.media.MediaInfo;
import moe.anitrack.thirdparties.common.model.presentation.ThirdpartyServiceInfo;

public interface ThirdpartyService {

    /**
     * @return unique name used to reference the service relating to authentication persistence.
     *
     * @implNote This should NEVER change as it would wipe the saved logging information
     */
    String uniqueName();

    void played(final MediaInfo mediaInfo);

    ThirdpartyAuthenticationService getAuthenticationService();

    ThirdpartyServiceInfo getChoiceInfo();

}
