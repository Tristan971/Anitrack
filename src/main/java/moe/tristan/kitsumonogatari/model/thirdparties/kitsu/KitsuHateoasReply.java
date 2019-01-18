package moe.tristan.kitsumonogatari.model.thirdparties.kitsu;

import org.immutables.value.Value.Immutable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Immutable
@JsonSerialize
@JsonDeserialize
public interface KitsuHateoasReply<T> {

    @JsonProperty("attributes")
    T getElement();

}
