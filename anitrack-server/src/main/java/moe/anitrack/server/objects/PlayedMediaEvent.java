package moe.anitrack.server.objects;

import org.immutables.value.Value.Immutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Immutable
@JsonSerialize(as = ImmutablePlayedMediaEvent.class)
@JsonDeserialize(as = ImmutablePlayedMediaEvent.class)
public interface PlayedMediaEvent {

    String getName();

    String getPlayer();

}
