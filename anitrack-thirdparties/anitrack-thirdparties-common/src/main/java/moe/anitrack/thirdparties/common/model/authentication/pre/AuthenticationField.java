package moe.anitrack.thirdparties.common.model.authentication.pre;

import java.util.Optional;
import java.util.function.Predicate;

import org.immutables.value.Value.Default;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

@Immutable
public interface AuthenticationField {

    /**
     * @return The name of the field that the user will see
     */
    @Parameter
    String getFieldName();

    /**
     * @return Whether to "hide" typed value (typical usage of dots in password fields)
     */
    @Parameter
    @Default
    default boolean isPasswordLike() {
        return false;
    }

    /**
     * @return An optional validator for pre-validation of the entered value
     */
    Optional<Predicate<String>> getValidator();

}
