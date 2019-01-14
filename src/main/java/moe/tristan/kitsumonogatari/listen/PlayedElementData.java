package moe.tristan.kitsumonogatari.listen;

import org.immutables.value.Value.Immutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Immutable
@JsonSerialize(as = ImmutablePlayedElementData.class)
@JsonDeserialize(as = ImmutablePlayedElementData.class)
public interface PlayedElementData {

    String getName();

    String getPlayer();

}
