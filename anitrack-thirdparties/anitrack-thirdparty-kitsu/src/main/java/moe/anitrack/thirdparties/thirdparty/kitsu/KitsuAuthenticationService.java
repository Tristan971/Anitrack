package moe.anitrack.thirdparties.thirdparty.kitsu;

import static io.vavr.API.unchecked;
import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.net.URI;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import moe.anitrack.thirdparties.common.ThirdpartyAuthenticationService;
import moe.anitrack.thirdparties.common.model.authentication.AuthenticationField;
import moe.anitrack.thirdparties.thirdparty.kitsu.objects.authentication.OauthPasswordAuthenticationResponse;

@Component
public class KitsuAuthenticationService implements ThirdpartyAuthenticationService<OauthPasswordAuthenticationResponse> {

    private static final String OAUTH_ENDPOINT = "https://kitsu.io/api/oauth/token";
    private static final String OAUTH_BODY_TEMPLATE = "grant_type=password&username=%s&password=%s";
    private static final String EMAIL_FIELD_NAME = "E-mail address";
    private static final String PASSWORD_FIELD_NAME = "Password";

    private final RestTemplate restTemplate;

    private final AuthenticationField emailField = AuthenticationField.builder().name(EMAIL_FIELD_NAME).build();
    private final AuthenticationField passwordField = AuthenticationField.of(PASSWORD_FIELD_NAME);
    private final AtomicReference<OauthPasswordAuthenticationResponse> authentication = new AtomicReference<>();

    @Autowired
    public KitsuAuthenticationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getName() {
        return "kitsu";
    }

    @Override
    public List<AuthenticationField> getAuthenticationFields() {
        return List.of(emailField, passwordField);
    }

    @Override
    public CompletableFuture<OauthPasswordAuthenticationResponse> tryAuthenticate(Map<AuthenticationField, String> submittedValues) {
        return CompletableFuture.supplyAsync(() -> {
            final String email = submittedValues.get(emailField);
            final String password = submittedValues.get(passwordField);
            final OauthPasswordAuthenticationResponse result = unchecked(() -> authenticateWith(email, password)).get();
            authentication.set(result);
            return result;
        });
    }

    @Override
    public void onFoundSavedCredentials(OauthPasswordAuthenticationResponse savedCredentials) {
        authentication.set(savedCredentials);
    }

    public Optional<OauthPasswordAuthenticationResponse> getAuthentication() {
        return Optional.ofNullable(authentication.get());
    }

    private OauthPasswordAuthenticationResponse authenticateWith(final String username, final String password) {
        final ResponseEntity<OauthPasswordAuthenticationResponse> responseEntity = restTemplate.exchange(
                buildAuthRequestEntity(username, password),
                OauthPasswordAuthenticationResponse.class
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            String errFmt = "Unexpected auth response status %s";
            throw new IllegalStateException(format(errFmt, responseEntity.getStatusCode()));
        }
    }

    private RequestEntity<String> buildAuthRequestEntity(final String username, final String password) {

        final String body = format(
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

}
