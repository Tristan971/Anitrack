package moe.anitrack.thirdparties.thirdparty.kitsu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import moe.anitrack.thirdparties.common.ThirdpartyAuthenticationService;
import moe.anitrack.thirdparties.common.ThirdpartyService;
import moe.anitrack.thirdparties.common.model.media.MediaInfo;
import moe.anitrack.thirdparties.common.model.presentation.ImmutableThirdpartyServiceChoiceInfo;
import moe.anitrack.thirdparties.common.model.presentation.ThirdpartyServiceChoiceInfo;

@Component
public class KitsuService implements ThirdpartyService<MediaInfo> {

    private static final Logger LOGGER = LoggerFactory.getLogger(KitsuService.class);

    private final KitsuAuthenticationService kitsuAuthenticationService;

    @Autowired
    public KitsuService(KitsuAuthenticationService kitsuAuthenticationService) {
        this.kitsuAuthenticationService = kitsuAuthenticationService;
    }

    @Override
    public void played(MediaInfo mediaInfo) {
        LOGGER.info("Dispatching played media query to Kitsu: {}", mediaInfo);
    }

    @Override
    public MediaInfo mapFromAnitrackMediaInfo(MediaInfo anitrackMediaInfo) {
        return anitrackMediaInfo;
    }

    @Override
    public ThirdpartyAuthenticationService getAuthenticationService() {
        return kitsuAuthenticationService;
    }

    @Override
    public ThirdpartyServiceChoiceInfo getChoiceInfo() {
        return ImmutableThirdpartyServiceChoiceInfo
                .builder()
                .name("Kitsu")
                .logoUrl(getClass().getClassLoader().getResource("kitsu-logo.jpg"))
                .build();
    }

}
