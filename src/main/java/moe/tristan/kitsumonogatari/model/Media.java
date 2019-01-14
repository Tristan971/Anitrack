package moe.tristan.kitsumonogatari.model;

import org.immutables.value.Value.Immutable;

import moe.tristan.kitsumonogatari.util.ApiStyle;

@Immutable
@ApiStyle
public interface Media {

    String getUuid();

}
