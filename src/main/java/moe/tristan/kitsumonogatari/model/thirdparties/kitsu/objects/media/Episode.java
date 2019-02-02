package moe.tristan.kitsumonogatari.model.thirdparties.kitsu.objects.media;

import java.time.LocalDate;

import org.immutables.value.Value.Immutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import moe.tristan.kitsumonogatari.model.thirdparties.kitsu.objects.media.common.CreatedAndUpdated;
import moe.tristan.kitsumonogatari.model.thirdparties.kitsu.objects.media.common.Titles;

@Immutable
@JsonSerialize(as = ImmutableEpisode.class)
@JsonDeserialize(as = ImmutableEpisode.class)
public interface Episode extends CreatedAndUpdated {

    Titles getTitles();

    String getCanonicalTitle();

    int getSeasonNumber();

    int getNumber();

    String getSynopsis();

    LocalDate getAirdate();

    int getLength();

}
