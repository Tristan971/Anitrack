package moe.anitrack.thirdparty.common.model;

import java.io.InputStream;
import java.util.function.Supplier;

import org.immutables.value.Value.Immutable;

import com.treatwell.immutables.styles.ValueObjectStyle;

@Immutable
@ValueObjectStyle
public abstract class AbstractThirdpartyInfo {

	public abstract String getName();

	public abstract Supplier<InputStream> getLogoProvider();

}
