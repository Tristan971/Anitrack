package moe.tristan.anitrack.thirdparties.thirdparty.kitsu.objects.media.common;

import java.util.Map;
import java.util.Optional;

import org.immutables.value.Value.Immutable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Immutable
@JsonSerialize
public interface MultipleSizeImageRef {

    Optional<String> getTiny();

    Optional<String> getSmall();

    Optional<String> getMedium();

    Optional<String> getLarge();

    String getOriginal();

    Map<String, String> getMeta();

}

