package moe.anitrack.thirdparties.common;

import moe.anitrack.thirdparties.common.objects.MediaInfo;

public interface ThirdpartyService<T> {

    void played(final T thirdpartyMediaInfo);

    T mapCommonMediaInfoToThirdparty(final MediaInfo commonMediaInfo);

}
