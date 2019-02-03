package moe.tristan.anitrack.thirdparties.thirdparty.kitsu.objects.media;

import java.time.LocalDate;

import org.immutables.value.Value.Immutable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import moe.tristan.anitrack.thirdparties.thirdparty.kitsu.objects.media.common.CreatedAndUpdated;
import moe.tristan.anitrack.thirdparties.thirdparty.kitsu.objects.media.common.Titles;

@Immutable
@JsonSerialize
public interface Episode extends CreatedAndUpdated {

    Titles getTitles();

    String getCanonicalTitle();

    int getSeasonNumber();

    int getNumber();

    String getSynopsis();

    LocalDate getAirdate();

    int getLength();

}
