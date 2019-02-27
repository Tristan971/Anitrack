package moe.anitrack.thirdparties.common;

import moe.anitrack.thirdparties.common.model.media.MediaInfo;
import moe.anitrack.thirdparties.common.model.presentation.ThirdpartyServiceChoiceInfo;

public interface ThirdpartyService<T> {

    /**
     * @return unique name used to reference the service relating to authentication persistence.
     *
     * @implNote This should NEVER change as it would wipe the saved logging information
     */
    String uniqueName();

    void played(final T mediaInfo);

    T mapFromAnitrackMediaInfo(final MediaInfo anitrackMediaInfo);

    ThirdpartyAuthenticationService getAuthenticationService();

    ThirdpartyServiceChoiceInfo getChoiceInfo();

}
