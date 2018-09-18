package moe.tristan.kitsumonogatari.model.events;

import java.time.LocalDate;

import org.immutables.value.Value.Immutable;

@Immutable
public interface PlayerEvent extends Event<PlayerEvent> {
    String getElementName();
    String getSourceName();
    LocalDate getEventCreatedAt();
}
