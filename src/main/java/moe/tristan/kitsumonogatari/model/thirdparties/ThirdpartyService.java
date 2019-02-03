package moe.tristan.kitsumonogatari.model.thirdparties;

import moe.tristan.kitsumonogatari.model.MediaInfo;

public interface ThirdpartyService<T> {

    void played(final T thirdpartyMediaInfo);

    T mapCommonMediaInfoToThirdparty(final MediaInfo commonMediaInfo);

}
