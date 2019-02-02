package moe.tristan.kitsumonogatari.model.thirdparties;

import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ThirdpartyConfiguration {

    private final ThirdpartyRequestsInterceptor thirdpartyRequestsInterceptor;

    public ThirdpartyConfiguration(ThirdpartyRequestsInterceptor thirdpartyRequestsInterceptor) {
        this.thirdpartyRequestsInterceptor = thirdpartyRequestsInterceptor;
    }

    @Bean
    public Supplier<RestTemplate> restTemplate() {
        return () -> {
            final RestTemplate restTemplate = new RestTemplate();
            restTemplate.getInterceptors().add(this.thirdpartyRequestsInterceptor);
            return restTemplate;
        };
    }

    @Primary
    @Bean
    public RestTemplate defaultRestTemplate() {
        return restTemplate().get();
    }

}