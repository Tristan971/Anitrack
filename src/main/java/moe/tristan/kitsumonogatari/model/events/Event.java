package moe.tristan.kitsumonogatari.model.events;

import java.util.UUID;

public interface Event<T extends Event<T>> {

    String eventUuid = UUID.randomUUID().toString();

    @SuppressWarnings("unchecked")
    default Class<T> getEventType() {
        return (Class<T>) getClass();
    }

    default String asString() {
        return eventUuid;
    }

}
