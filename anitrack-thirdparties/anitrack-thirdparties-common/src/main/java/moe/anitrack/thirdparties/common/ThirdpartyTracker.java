package moe.anitrack.thirdparties.common;

import moe.anitrack.thirdparties.common.model.media.MediaInfo;

public interface ThirdpartyTracker {

    void played(final MediaInfo mediaInfo);

}
