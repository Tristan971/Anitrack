package moe.anitrack.server.reactive;

import static java.util.concurrent.CompletableFuture.runAsync;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import moe.anitrack.server.objects.PlayedMediaEvent;
import moe.anitrack.server.reactive.bindings.PlayedMediaEventListener;

@Component
public class PlayedMediaEventDispatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayedMediaEventDispatcher.class);

    private final List<PlayedMediaEventListener> playedMediaEventListeners = new LinkedList<>();

    public void subscribe(@NonNull final PlayedMediaEventListener eventListener) {
        if (playedMediaEventListeners.contains(Objects.requireNonNull(eventListener, "Tried subscribing with null listener!"))) {
            throw new IllegalArgumentException("You tried subscribing to played media reactive twice with the same listener: " + eventListener);
        }

        playedMediaEventListeners.add(eventListener);
    }

    public void eventReceived(@NonNull final PlayedMediaEvent event) {
        Objects.requireNonNull(event, "Tried dispatching null event!");
        playedMediaEventListeners.forEach(listener -> notifyListenerOfEvent(listener, event));
    }

    private void notifyListenerOfEvent(final PlayedMediaEventListener eventListener, final PlayedMediaEvent event) {
        runAsync(() -> {
            LOGGER.debug("Notified {} of event {}", eventListener, event);
            eventListener.onPlayedEvent(event);
            LOGGER.debug("Listener {} finished processing {}.", eventListener, event);
        });
    }

}
