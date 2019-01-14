package moe.tristan.kitsumonogatari.listen.objects;

import org.immutables.value.Value.Immutable;

import moe.tristan.kitsumonogatari.util.ApiStyle;

@Immutable
@ApiStyle
public interface PlayedElementData {

    String getName();

    NameStyle getNameStyle();

    String getPlayer();

}
