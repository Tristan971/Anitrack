package moe.anitrack.thirdparties.common.model.presentation;

import java.net.URL;

import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

import com.treatwell.immutables.styles.ValueObjectStyle;

@Immutable
@ValueObjectStyle
abstract class AbstractThirdpartyInfo {

    @Parameter
    public abstract String getName();

    @Parameter
    public abstract URL getLogo();

}
