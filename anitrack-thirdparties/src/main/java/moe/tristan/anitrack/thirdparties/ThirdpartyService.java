package moe.tristan.anitrack.thirdparties;

import moe.tristan.anitrack.thirdparties.common.MediaInfo;

public interface ThirdpartyService<T> {

    void played(final T thirdpartyMediaInfo);

    T mapCommonMediaInfoToThirdparty(final MediaInfo commonMediaInfo);

}
