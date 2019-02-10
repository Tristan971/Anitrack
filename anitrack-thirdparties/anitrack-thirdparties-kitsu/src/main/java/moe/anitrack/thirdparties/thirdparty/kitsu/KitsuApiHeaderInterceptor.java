package moe.anitrack.thirdparties.thirdparty.kitsu;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class KitsuApiHeaderInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(KitsuApiHeaderInterceptor.class);

    private static final String CONTENT_TYPE_OVERRIDE = "application/vnd.api+json";

    private final KitsuAuthenticationService kitsuAuthenticationService;

    @Autowired
    public KitsuApiHeaderInterceptor(KitsuAuthenticationService kitsuAuthenticationService) {
        this.kitsuAuthenticationService = kitsuAuthenticationService;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        LOGGER.debug("Injecting headers : [{}, {}] -> {}", CONTENT_TYPE, ACCEPT, CONTENT_TYPE_OVERRIDE);
        final HttpHeaders requestHeaders = request.getHeaders();
        requestHeaders.set(CONTENT_TYPE, CONTENT_TYPE_OVERRIDE);
        requestHeaders.set(ACCEPT, CONTENT_TYPE_OVERRIDE);

        kitsuAuthenticationService.getAuthentication().ifPresent(oauthResponse -> {
            LOGGER.debug("Authenticated with Kitsu, injecting bearer authorization token.");
            requestHeaders.setBearerAuth(oauthResponse.getAccessToken());
        });

        return execution.execute(request, body);
    }

}
