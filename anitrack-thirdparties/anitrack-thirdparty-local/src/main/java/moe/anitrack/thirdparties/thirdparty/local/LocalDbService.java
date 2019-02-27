package moe.anitrack.thirdparties.thirdparty.local;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import moe.anitrack.thirdparties.common.ThirdpartyAuthenticationService;
import moe.anitrack.thirdparties.common.ThirdpartyService;
import moe.anitrack.thirdparties.common.model.media.MediaInfo;
import moe.anitrack.thirdparties.common.model.presentation.ImmutableThirdpartyServiceChoiceInfo;
import moe.anitrack.thirdparties.common.model.presentation.ThirdpartyServiceChoiceInfo;
import moe.anitrack.thirdparties.thirdparty.local.model.LocalSimpleMedia;
import moe.anitrack.thirdparties.thirdparty.local.repository.LocalDbRepository;

@Component
public class LocalDbService implements ThirdpartyService<MediaInfo> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalDbService.class);

    private final LocalDbRepository localDbRepository;
    private final LocalDbAuthService dummyAuthService;

    public LocalDbService(LocalDbRepository localDbRepository, LocalDbAuthService dummyAuthService) {
        this.localDbRepository = localDbRepository;
        this.dummyAuthService = dummyAuthService;
    }

    @Override
    public String uniqueName() {
        return "local-db-service";
    }

    @Override
    public void played(MediaInfo mediaInfo) {
        final LocalSimpleMedia saved = localDbRepository.save(new LocalSimpleMedia(mediaInfo.getName(), mediaInfo));
        LOGGER.info("Marked {} as played in local db!", saved);
    }

    @Override
    public MediaInfo mapFromAnitrackMediaInfo(MediaInfo anitrackMediaInfo) {
        return anitrackMediaInfo;
    }

    @Override
    public ThirdpartyAuthenticationService getAuthenticationService() {
        return dummyAuthService;
    }

    @Override
    public ThirdpartyServiceChoiceInfo getChoiceInfo() {
        return ImmutableThirdpartyServiceChoiceInfo
                .builder()
                .name("Local database")
                .logoUrl(getClass().getClassLoader().getResource("cloud.png"))
                .build();
    }

}
