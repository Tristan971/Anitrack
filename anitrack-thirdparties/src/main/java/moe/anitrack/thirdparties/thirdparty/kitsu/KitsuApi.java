package moe.anitrack.thirdparties.thirdparty.kitsu;

import org.springframework.stereotype.Component;

import moe.anitrack.thirdparties.ThirdpartyService;
import moe.anitrack.thirdparties.common.MediaInfo;

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
