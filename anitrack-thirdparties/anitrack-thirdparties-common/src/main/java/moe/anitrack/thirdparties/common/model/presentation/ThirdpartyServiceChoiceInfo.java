package moe.anitrack.thirdparties.common.model.presentation;

import java.net.URL;

import org.immutables.value.Value.Immutable;

@Immutable
public interface ThirdpartyServiceChoiceInfo {

    String getName();

    URL getLogoUrl();

}
