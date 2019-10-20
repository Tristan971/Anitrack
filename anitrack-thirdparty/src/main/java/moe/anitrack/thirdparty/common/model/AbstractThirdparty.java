package moe.anitrack.thirdparty.common.model;

import org.immutables.value.Value.Immutable;

import com.treatwell.immutables.styles.ValueObjectStyle;

import moe.tristan.anitrack.commons.ddd.IdentifiedBy;

@Immutable
@ValueObjectStyle
public abstract class AbstractThirdparty implements IdentifiedBy<String> {

	public abstract ThirdpartyInfo getInfo();

	public abstract Class<? extends ThirdpartyClient> getClientClass();

}
