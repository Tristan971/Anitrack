package moe.tristan.kitsumonogatari.model.media;

import org.immutables.value.Value;

@Value.Immutable
public interface Media {
    String getTitle();
    MediaInfo getMediaInfo();
}
