package moe.anitrack.thirdparty.common;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import moe.anitrack.thirdparty.common.http.ThirdpartyRequestsLoggingInterceptor;

@Configuration
public class ThirdpartiesCommonConfiguration {

	@Bean
	public RestTemplateCustomizer loggingRestTemplateCustomizer() {
		return restTemplate -> restTemplate.getInterceptors().add(0, new ThirdpartyRequestsLoggingInterceptor());
	}

}
