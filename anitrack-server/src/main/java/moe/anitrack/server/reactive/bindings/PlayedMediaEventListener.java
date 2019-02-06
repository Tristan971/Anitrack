package moe.anitrack.server.reactive.bindings;

import moe.anitrack.server.objects.PlayedMediaEvent;

public interface PlayedMediaEventListener {

    void onPlayedEvent(final PlayedMediaEvent playedMediaEvent);

}
