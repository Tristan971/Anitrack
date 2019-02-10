package moe.anitrack.thirdparties.common;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("moe.anitrack.thirdparties.common")
public class ThirdpartyBaseConfiguration {

    @Bean
    @Primary
    @Scope(SCOPE_PROTOTYPE)
    public RestTemplate defaultRestTemplate(final ThirdpartyRequestsInterceptor requestsInterceptor) {
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(requestsInterceptor);
        return restTemplate;
    }

}
