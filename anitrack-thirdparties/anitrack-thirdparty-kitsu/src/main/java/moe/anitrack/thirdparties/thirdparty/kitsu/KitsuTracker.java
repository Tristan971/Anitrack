package moe.anitrack.thirdparties.thirdparty.kitsu;

import org.springframework.stereotype.Component;

import moe.anitrack.thirdparties.common.ThirdpartyTracker;
import moe.anitrack.thirdparties.common.model.media.MediaInfo;

@Component
public class KitsuTracker implements ThirdpartyTracker {

    @Override
    public void played(MediaInfo mediaInfo) {

    }

}
