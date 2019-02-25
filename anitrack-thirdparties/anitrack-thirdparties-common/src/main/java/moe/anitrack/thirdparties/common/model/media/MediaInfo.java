package moe.anitrack.thirdparties.common.model.media;

import org.immutables.value.Value.Immutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Immutable
@JsonSerialize(as = ImmutableMediaInfo.class)
@JsonDeserialize(as = ImmutableMediaInfo.class)
public interface MediaInfo {

    String getName();

    String getFileName();

    int getUserScore();

}
