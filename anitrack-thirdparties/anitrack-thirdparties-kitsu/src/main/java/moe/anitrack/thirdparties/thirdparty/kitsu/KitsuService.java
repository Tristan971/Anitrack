package moe.anitrack.thirdparties.thirdparty.kitsu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import moe.anitrack.thirdparties.common.ThirdpartyService;
import moe.anitrack.thirdparties.common.objects.MediaInfo;

@Component
public class KitsuService implements ThirdpartyService<MediaInfo> {

    private static final Logger LOGGER = LoggerFactory.getLogger(KitsuService.class);

    @Override
    public void played(MediaInfo thirdpartyMediaInfo) {
        LOGGER.info("Dispatching played media query to Kitsu: {}", thirdpartyMediaInfo);
    }

    @Override
    public MediaInfo mapCommonMediaInfoToThirdparty(MediaInfo commonMediaInfo) {
        return commonMediaInfo;
    }

}
