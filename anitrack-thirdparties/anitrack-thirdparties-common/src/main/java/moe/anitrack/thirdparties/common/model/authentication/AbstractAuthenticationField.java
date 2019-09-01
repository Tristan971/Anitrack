package moe.anitrack.thirdparties.common.model.authentication;

import org.immutables.value.Value.Default;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

import com.treatwell.immutables.styles.ValueObjectStyle;

@Immutable
@ValueObjectStyle
abstract class AbstractAuthenticationField {

    @Parameter
    public abstract String getName();

    @Default
    public boolean isSecret() {
        return true;
    }

}
