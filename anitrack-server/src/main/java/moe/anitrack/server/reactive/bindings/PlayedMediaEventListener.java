package moe.anitrack.server.reactive.bindings;

import org.springframework.lang.NonNull;

import moe.anitrack.server.objects.PlayedMediaEvent;

public interface PlayedMediaEventListener {

    void onPlayedEvent(@NonNull final PlayedMediaEvent playedMediaEvent);

}
