package moe.anitrack.thirdparties.thirdparty.kitsu.objects.authentication;

import java.time.Duration;
import java.time.Instant;

import org.immutables.value.Value.Immutable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Immutable
@JsonSerialize(as = ImmutableOauthPasswordAuthenticationResponse.class)
@JsonDeserialize(as = ImmutableOauthPasswordAuthenticationResponse.class)
public interface OauthPasswordAuthenticationResponse {

    @JsonProperty("access_token")
    String getAccessToken();

    @JsonProperty("created_at")
    Instant getCreatedAt();

    @JsonProperty("expires_in")
    Duration getExpiresIn();

    @JsonProperty("refresh_token")
    String getRefreshToken();

    String getScope();

    @JsonProperty("token_type")
    String getTokenType();

}
