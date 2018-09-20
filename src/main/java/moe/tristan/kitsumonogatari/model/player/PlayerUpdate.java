package moe.tristan.kitsumonogatari.model.player;

import org.immutables.value.Value;

@Value.Immutable
public interface PlayerUpdate {
    String getTitle();
    String getPlayerName();
}
