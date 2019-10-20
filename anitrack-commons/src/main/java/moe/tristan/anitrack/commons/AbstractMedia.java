package moe.tristan.anitrack.commons;

import java.util.Map;

import org.immutables.value.Value.Immutable;

import com.treatwell.immutables.styles.ValueObjectStyle;

import moe.tristan.anitrack.commons.ddd.IdentifiedBy;

@Immutable
@ValueObjectStyle
public abstract class AbstractMedia implements IdentifiedBy<Long> {

	public abstract String getName();

	public abstract Map<String, String> getProperties();

}
