package moe.anitrack.listener;

import org.immutables.value.Value.Immutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Immutable
@JsonSerialize(as = ImmutableInfoData.class)
@JsonDeserialize(as = ImmutableInfoData.class)
public interface InfoData {

    String getApplicationVersion();

}
