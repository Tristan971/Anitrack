package moe.tristan.kitsumonogatari.model.thirdparties.kitsu.objects.media.common;

import java.util.Map;
import java.util.Optional;

import org.immutables.value.Value.Immutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Immutable
@JsonSerialize(as = ImmutableMultipleSizeImageRef.class)
@JsonDeserialize(as = ImmutableMultipleSizeImageRef.class)
public interface MultipleSizeImageRef {

    Optional<String> getTiny();

    Optional<String> getSmall();

    Optional<String> getMedium();

    Optional<String> getLarge();

    String getOriginal();

    Map<String, String> getMeta();

}

