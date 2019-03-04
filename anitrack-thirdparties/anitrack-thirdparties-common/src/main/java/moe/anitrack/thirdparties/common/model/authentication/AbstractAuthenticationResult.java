package moe.anitrack.thirdparties.common.model.authentication;

import java.util.Map;

import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

import moe.anitrack.base.util.ValueObjectStyle;

@Immutable
@ValueObjectStyle
public abstract class AbstractAuthenticationResult {

    @Parameter
    public abstract Map<AuthenticationField, String> getAuthenticationData();

}
