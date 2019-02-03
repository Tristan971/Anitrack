package moe.tristan.kitsumonogatari.model;

import org.immutables.value.Value.Immutable;

@Immutable
public interface MediaInfo {

    String getName();

    String getFileName();

    int getUserScore();

}
