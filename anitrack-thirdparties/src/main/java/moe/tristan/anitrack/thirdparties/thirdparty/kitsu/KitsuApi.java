package moe.tristan.anitrack.thirdparties.thirdparty.kitsu;

import org.springframework.stereotype.Component;

import moe.tristan.anitrack.thirdparties.ThirdpartyService;
import moe.tristan.anitrack.thirdparties.common.MediaInfo;

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
