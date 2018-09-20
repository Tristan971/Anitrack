package moe.tristan.kitsumonogatari.thirdparties;

import moe.tristan.kitsumonogatari.model.media.Media;

import io.vavr.control.Try;

public interface TrackingService<MEDIA_MODEL extends Media> {

    Try<MEDIA_MODEL> searchMedia(final String title);

}
