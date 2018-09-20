package moe.tristan.kitsumonogatari.thirdparties.kitsu;

import org.springframework.stereotype.Service;

import moe.tristan.kitsumonogatari.model.media.Media;
import moe.tristan.kitsumonogatari.thirdparties.TrackingService;

import io.vavr.control.Try;

@Service
public class Kitsu implements TrackingService {

    @Override
    public Try<Media> searchMedia(String title) {
        return null;
    }

}
