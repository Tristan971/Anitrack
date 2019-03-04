package moe.anitrack.base.util;

import org.immutables.value.Value.Style;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Style(
        typeImmutable = "*"
)
@JsonSerialize
public @interface ValueObjectStyle {

}
