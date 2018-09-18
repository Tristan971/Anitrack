package moe.tristan.kitsumonogatari.model;

import static io.vavr.API.Set;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vavr.collection.Set;
import moe.tristan.kitsumonogatari.model.events.Event;
import moe.tristan.kitsumonogatari.model.listeners.EventListener;

public final class EventDispatcher {

    private static final Logger LOG = LoggerFactory.getLogger(EventDispatcher.class);

    private static final ExecutorService DISPATCHER = Executors.newCachedThreadPool();
    private static final Map<Class<? extends Event>, Set<EventListener>> LISTENERS = new ConcurrentHashMap<>();

    public static void suscribe(EventListener self, Class<? extends Event>... eventTypes) {
        Arrays.stream(eventTypes)
              .forEach(eventType -> LISTENERS.compute(
                      eventType,
                      (__, eventListeners) -> eventListeners == null ? Set(self) : eventListeners.add(self)
              ));
    }

    public static void send(final Event event) {
        LOG.debug("Will send event {} [{}]", event.asString(), event);
        DISPATCHER.submit(() -> LISTENERS.get(event.getEventType()).forEach(listener -> {
            LOG.debug("Dispatching event#{} to listener {}", event.eventUuid, listener);
            listener.notify(event);
        }));
    }

}
