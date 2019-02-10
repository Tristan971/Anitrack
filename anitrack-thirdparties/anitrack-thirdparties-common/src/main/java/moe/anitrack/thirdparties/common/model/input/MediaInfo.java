package moe.anitrack.thirdparties.common.model.input;

import org.immutables.value.Value.Immutable;

@Immutable
public interface MediaInfo {

    String getName();

    String getFileName();

    int getUserScore();

}
