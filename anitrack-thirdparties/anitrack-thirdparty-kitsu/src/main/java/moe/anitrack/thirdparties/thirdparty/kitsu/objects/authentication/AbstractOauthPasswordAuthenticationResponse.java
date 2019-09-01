package moe.anitrack.thirdparties.thirdparty.kitsu.objects.authentication;

import org.immutables.value.Value.Immutable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.treatwell.immutables.styles.ValueObjectStyle;

@Immutable
@ValueObjectStyle
abstract class AbstractOauthPasswordAuthenticationResponse {

    @JsonProperty("access_token")
    public abstract String getAccessToken();

    @JsonProperty("created_at")
    public abstract int getCreatedAt();

    @JsonProperty("expires_in")
    public abstract int getExpiresInSeconds();

    @JsonProperty("refresh_token")
    public abstract String getRefreshToken();

    public abstract String getScope();

    @JsonProperty("token_type")
    public abstract String getTokenType();

}
