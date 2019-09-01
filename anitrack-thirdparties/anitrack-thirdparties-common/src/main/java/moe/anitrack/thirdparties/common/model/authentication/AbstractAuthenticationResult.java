package moe.anitrack.thirdparties.common.model.authentication;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS;

import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.treatwell.immutables.styles.ValueObjectStyle;

@Immutable
@ValueObjectStyle
abstract class AbstractAuthenticationResult<T> {

    @Parameter
    @JsonTypeInfo(use = CLASS)
    public abstract T getResult();

}
