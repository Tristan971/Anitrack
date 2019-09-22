package moe.anitrack.thirdparties.thirdparty.local;

import org.springframework.stereotype.Component;

import moe.anitrack.thirdparties.common.ThirdpartyTracker;
import moe.anitrack.thirdparties.common.model.media.MediaInfo;
import moe.anitrack.thirdparties.thirdparty.local.model.LocalMediaEntity;
import moe.anitrack.thirdparties.thirdparty.local.repository.LocalDatabaseRepository;

@Component
public class LocalDatabaseTracker implements ThirdpartyTracker {

    private final LocalDatabaseRepository localDatabaseRepository;

    public LocalDatabaseTracker(LocalDatabaseRepository localDatabaseRepository) {
        this.localDatabaseRepository = localDatabaseRepository;
    }

    @Override
    public void played(MediaInfo mediaInfo) {
        LocalMediaEntity mediaEntity = localDatabaseRepository
                .findById(mediaInfo.getName())
                .orElseGet(() -> new LocalMediaEntity(mediaInfo.getName(), mediaInfo, 0));

        mediaEntity.setPlayCount(mediaEntity.getPlayCount() + 1);
        localDatabaseRepository.save(mediaEntity);
    }

}
