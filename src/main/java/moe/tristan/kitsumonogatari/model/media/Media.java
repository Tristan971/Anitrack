package moe.tristan.kitsumonogatari.model.media;

import org.immutables.value.Value;

import io.vavr.control.Option;

@Value.Immutable
public interface Media {

    String getTitle();

    @Value.Default
    default Option<Media> getEnclosingSeries() {
        return Option.none();
    }

    @Value.Derived
    default boolean hasEnclosingSeries() {
        Boolean hasOrSelf = getEnclosingSeries().map(Media::getTitle).map(getTitle()::equals).getOrElse(true);
        return !hasOrSelf;
    }

}
