package moe.anitrack.thirdparty.common.http;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class AddHeaderInterceptor implements ClientHttpRequestInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddHeaderInterceptor.class);

	private final ExtraHeadersProvider extraHeadersProvider;

	public AddHeaderInterceptor(ExtraHeadersProvider extraHeadersProvider) {
		this.extraHeadersProvider = extraHeadersProvider;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
	throws IOException {
		Map<String, List<String>> addedHeaders = extraHeadersProvider.getExtraHeaders();

		HttpHeaders originalHeaders = request.getHeaders();
		originalHeaders.putAll(addedHeaders);

		LOGGER.debug("Added headers: {}", addedHeaders);

		return execution.execute(request, body);
	}

	@FunctionalInterface
	public interface ExtraHeadersProvider {

		Map<String, List<String>> getExtraHeaders();

	}

}
