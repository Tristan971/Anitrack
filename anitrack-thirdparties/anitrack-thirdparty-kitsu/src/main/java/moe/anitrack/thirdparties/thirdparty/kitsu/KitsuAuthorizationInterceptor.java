package moe.anitrack.thirdparties.thirdparty.kitsu;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class KitsuAuthorizationInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(KitsuAuthorizationInterceptor.class);

    private final KitsuAuthenticationService kitsuAuthenticationService;

    private final String clientId;
    private final String clientSecret;

    public KitsuAuthorizationInterceptor(KitsuAuthenticationService kitsuAuthenticationService, Environment environment) {
        this.kitsuAuthenticationService = kitsuAuthenticationService;
        this.clientId = environment.getRequiredProperty("CLIENT_ID");
        this.clientSecret = environment.getRequiredProperty("CLIENT_SECRET");
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        kitsuAuthenticationService.getAuthentication().ifPresent(authentication -> {
            LOGGER.debug("Detected authenticated user, injecting authorization access token into request.");
            request.getHeaders().add(AUTHORIZATION, "Bearer" + authentication.getAccessToken());
        });

        request.getHeaders().add("CLIENT_ID", clientId);
        request.getHeaders().add("CLIENT_SECRET", clientSecret);

        return execution.execute(request, body);
    }

}
