package moe.anitrack.thirdparties;

import moe.anitrack.thirdparties.common.MediaInfo;

public interface ThirdpartyService<T> {

    void played(final T thirdpartyMediaInfo);

    T mapCommonMediaInfoToThirdparty(final MediaInfo commonMediaInfo);

}
