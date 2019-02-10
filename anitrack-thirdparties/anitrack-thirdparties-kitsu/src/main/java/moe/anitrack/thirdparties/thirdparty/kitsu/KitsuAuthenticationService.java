package moe.anitrack.thirdparties.thirdparty.kitsu;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import moe.anitrack.thirdparties.thirdparty.kitsu.objects.authentication.ImmutableOauthPasswordAuthenticationResponse;
import moe.anitrack.thirdparties.thirdparty.kitsu.objects.authentication.OauthPasswordAuthenticationResponse;

@Component
public class KitsuAuthenticationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KitsuAuthenticationService.class);

    private static final String OAUTH_ENDPOINT = "https://kitsu.io/api/oauth/token";
    private static final String OAUTH_BODY_TEMPLATE = "grant_type=password&username=%s&password=%s";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final AtomicReference<OauthPasswordAuthenticationResponse> authentication;

    public KitsuAuthenticationService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.authentication = new AtomicReference<>();
    }

    public void authenticateWith(final String username, final String password) throws IOException {
        final ResponseEntity<String> responseEntity = restTemplate.exchange(
                buildAuthRequestEntity(username, password),
                String.class
        );

        final String responseBody = responseEntity.getBody();

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            LOGGER.debug("Authentication Response : {}", responseBody);
            final OauthPasswordAuthenticationResponse authenticationResponse = objectMapper.readValue(
                    responseBody,
                    ImmutableOauthPasswordAuthenticationResponse.class
            );
            authentication.set(authenticationResponse);
        } else {
            throw new RuntimeException("Could not authenticate!\n " + responseBody);
        }
    }

    private RequestEntity<String> buildAuthRequestEntity(final String username, final String password) {

        final String body = String.format(
                OAUTH_BODY_TEMPLATE,
                URLEncoder.encode(username, UTF_8),
                URLEncoder.encode(password, UTF_8)
        );

        final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(CONTENT_TYPE, APPLICATION_FORM_URLENCODED_VALUE);
        headers.add(ACCEPT, APPLICATION_JSON_UTF8_VALUE);

        return new RequestEntity<>(
                body,
                headers,
                POST,
                URI.create(OAUTH_ENDPOINT)
        );
    }

    public Optional<OauthPasswordAuthenticationResponse> getAuthentication() {
        return Optional.ofNullable(authentication.get());
    }

}
