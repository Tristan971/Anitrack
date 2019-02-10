package moe.anitrack.thirdparties.thirdparty.kitsu;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import moe.anitrack.thirdparties.thirdparty.kitsu.objects.authentication.ImmutableOauthPasswordAuthenticationResponse;
import moe.anitrack.thirdparties.thirdparty.kitsu.objects.authentication.OauthPasswordAuthenticationResponse;

@Component
public class KitsuAuthenticationService {

    private static final String OAUTH_ENDPOINT = "https://kitsu.io/api/oauth/token";

    private static final String OAUTH_BODY_TEMPLATE = "grant_type=password\n"
                                                      + "&username=%s\n"
                                                      + "&password=%s";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final AtomicReference<OauthPasswordAuthenticationResponse> authentication;

    public KitsuAuthenticationService(@Qualifier("kitsu") RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.authentication = new AtomicReference<>();
    }

    public void authenticateWith(final String username, final String password) throws IOException {
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                OAUTH_ENDPOINT,
                buildBrokenAuthBody(username, password),
                String.class
        );
        final String responseBody = responseEntity.getBody();

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            final OauthPasswordAuthenticationResponse authenticationResponse = objectMapper.readValue(
                    responseBody,
                    ImmutableOauthPasswordAuthenticationResponse.class
            );
            authentication.set(authenticationResponse);
        } else {
            throw new RuntimeException("Could not authenticate!\n " + responseBody);
        }
    }

    private String buildBrokenAuthBody(final String username, final String password) {
        return String.format(OAUTH_BODY_TEMPLATE, username, password);
    }

    public Optional<OauthPasswordAuthenticationResponse> getAuthentication() {
        return Optional.ofNullable(authentication.get());
    }

}
