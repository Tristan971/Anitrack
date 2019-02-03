package moe.tristan.anitrack.thirdparties.thirdparty.kitsu;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class KitsuApiHateoasInterceptor implements ClientHttpRequestInterceptor {

    private final ObjectMapper objectMapper;

    @Autowired
    public KitsuApiHateoasInterceptor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution) throws IOException {
        final ClientHttpResponse response = execution.execute(request, body);
        final String unwrappedBody = unwrapResponseFromHateoas(response.getBody());
        return responseCodyWithBody(response, unwrappedBody);
    }

    private String unwrapResponseFromHateoas(final InputStream originalResponsebody) throws IOException {
        final JsonNode tree = objectMapper.readTree(originalResponsebody);
        final JsonNode actualElementTree = tree.get("data").get("attributes");
        return objectMapper.writeValueAsString(actualElementTree);
    }

    private ClientHttpResponse responseCodyWithBody(ClientHttpResponse response, String unwrappedBody) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return response.getStatusCode();
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return response.getRawStatusCode();
            }

            @Override
            public String getStatusText() throws IOException {
                return response.getStatusText();
            }

            @Override
            public void close() {
                response.close();
            }

            @Override
            public InputStream getBody() {
                return new ByteArrayInputStream(unwrappedBody.getBytes(StandardCharsets.UTF_8));
            }

            @Override
            public HttpHeaders getHeaders() {
                return response.getHeaders();
            }
        };
    }

}
