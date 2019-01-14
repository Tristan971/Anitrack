package moe.tristan.kitsumonogatari.util;

import org.immutables.value.Value.Style;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Style
@JsonSerialize
@JsonDeserialize
public @interface ApiStyle {

}
