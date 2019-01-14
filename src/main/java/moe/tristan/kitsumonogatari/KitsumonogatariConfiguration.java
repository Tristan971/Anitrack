package moe.tristan.kitsumonogatari;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import moe.tristan.kitsumonogatari.model.thirdparties.ThirdpartyRequestsInterceptor;

@Configuration
public class KitsumonogatariConfiguration {

    @Bean
    public RestTemplate restTemplate(final ThirdpartyRequestsInterceptor thirdpartyRequestsInterceptor) {
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(thirdpartyRequestsInterceptor);
        return restTemplate;
    }

}
