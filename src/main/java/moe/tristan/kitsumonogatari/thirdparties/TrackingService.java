package moe.tristan.kitsumonogatari.thirdparties;

import moe.tristan.kitsumonogatari.model.media.Media;

import io.vavr.control.Try;

public interface TrackingService {

    Try<Media> searchMedia(final String title);

}
