package moe.anitrack.thirdparty.common.http;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class ThirdpartyRequestsLoggingInterceptor implements ClientHttpRequestInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(ThirdpartyRequestsLoggingInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution execution)
	throws IOException {
		long start = System.currentTimeMillis();

		LOGGER.info("{}: {}", httpRequest.getMethod(), httpRequest.getURI());
		ClientHttpResponse response = execution.execute(httpRequest, bytes);

		long end = System.currentTimeMillis();

		LOGGER.debug(
			"{}: {} ({} - {})ms",
			httpRequest.getMethod(),
			httpRequest.getURI(),
			response.getRawStatusCode(),
			(end - start)
		);

		return response;
	}

}
