package moe.anitrack.thirdparties.common.dummy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import moe.anitrack.thirdparties.common.ThirdpartyService;
import moe.anitrack.thirdparties.common.objects.MediaInfo;

/**
 * Should you want only notifications for some reason (just testing the app, only like notifications, etc), we offer a dummy {@link ThirdpartyService}.
 */
@Component
public class DummyThirdpartyService implements ThirdpartyService<MediaInfo> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DummyThirdpartyService.class);

    @Override
    public void played(MediaInfo mediaInfo) {
        LOGGER.info("Received played media info notification. Ignoring as this is a dummy implementation. Notification was: {}", mediaInfo);
    }

    @Override
    public MediaInfo mapFromAnitrackMediaInfo(MediaInfo anitrackMediaInfo) {
        return anitrackMediaInfo;
    }

}
