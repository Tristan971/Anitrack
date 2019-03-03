package moe.anitrack.thirdparties.common.model.presentation;

import java.net.URL;

import org.immutables.value.Value.Immutable;

@Immutable
public interface ThirdpartyServiceInfo {

    String getName();

    URL getLogoUrl();

}
