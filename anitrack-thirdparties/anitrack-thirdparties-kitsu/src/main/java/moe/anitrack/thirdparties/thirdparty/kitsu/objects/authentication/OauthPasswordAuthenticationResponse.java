package moe.anitrack.thirdparties.thirdparty.kitsu.objects.authentication;

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
    int getCreatedAt();

    @JsonProperty("expires_in")
    int getExpiresInSeconds();

    @JsonProperty("refresh_token")
    String getRefreshToken();

    String getScope();

    @JsonProperty("token_type")
    String getTokenType();

}
