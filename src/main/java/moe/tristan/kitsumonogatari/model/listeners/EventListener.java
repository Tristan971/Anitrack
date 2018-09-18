package moe.tristan.kitsumonogatari.model.listeners;

import moe.tristan.kitsumonogatari.model.events.Event;

public interface EventListener {

    void notify(final Event event);

}
