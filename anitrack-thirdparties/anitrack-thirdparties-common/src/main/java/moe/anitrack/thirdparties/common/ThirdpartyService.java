package moe.anitrack.thirdparties.common;

import moe.anitrack.thirdparties.common.model.media.MediaInfo;

public interface ThirdpartyService<T> {

    void played(final T mediaInfo);

    T mapFromAnitrackMediaInfo(final MediaInfo anitrackMediaInfo);

    ThirdpartyAuthenticationService getAuthenticationService();

}
