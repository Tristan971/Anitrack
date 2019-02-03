package moe.tristan.kitsumonogatari.model.thirdparties.kitsu;

import org.springframework.stereotype.Component;

import moe.tristan.kitsumonogatari.model.MediaInfo;
import moe.tristan.kitsumonogatari.model.thirdparties.ThirdpartyService;

@Component
public class KitsuApi implements ThirdpartyService<MediaInfo> {

    @Override
    public void played(MediaInfo thirdpartyMediaInfo) {

    }

    @Override
    public MediaInfo mapCommonMediaInfoToThirdparty(MediaInfo commonMediaInfo) {
        return commonMediaInfo;
    }

}
