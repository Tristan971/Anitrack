package moe.anitrack.thirdparties.common.model.authentication;

import java.util.Optional;
import java.util.function.Predicate;

import org.immutables.value.Value.Default;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

import moe.anitrack.base.util.ValueObjectStyle;

@Immutable
@ValueObjectStyle
public abstract class AbstractAuthenticationField {

    /**
     * @return The name of the field that the user will see
     */
    @Parameter
    public abstract String getFieldName();

    /**
     * @return Whether to "hide" typed value (typical usage of dots in password fields)
     */
    @Parameter
    @Default
    public boolean isPasswordLike() {
        return false;
    }

    /**
     * @return An optional validator for pre-validation of the entered value
     */
    public abstract Optional<Predicate<String>> getValidator();

}
