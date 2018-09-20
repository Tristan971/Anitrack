package moe.tristan.kitsumonogatari.model.media;

import org.immutables.value.Value;

import io.vavr.control.Option;

@Value.Immutable
public interface MediaInfo {
    MediaType getMediaType();
    Option<Media> getEnclosingMedia();
}
