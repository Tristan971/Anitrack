package moe.anitrack.thirdparties.thirdparty.kitsu;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class KitsuAuthorizationInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(KitsuAuthorizationInterceptor.class);

    private final KitsuAuthenticationService kitsuAuthenticationService;

    public KitsuAuthorizationInterceptor(KitsuAuthenticationService kitsuAuthenticationService) {
        this.kitsuAuthenticationService = kitsuAuthenticationService;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        kitsuAuthenticationService.getAuthentication().ifPresent(authentication -> {
            LOGGER.debug("Detected authenticated user, injecting authorization access token into request.");
            request.getHeaders().add(AUTHORIZATION, "Bearer" + authentication.getAccessToken());
        });

        return execution.execute(request, body);
    }

}
