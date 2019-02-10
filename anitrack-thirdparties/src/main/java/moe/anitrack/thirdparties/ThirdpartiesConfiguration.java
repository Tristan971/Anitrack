package moe.anitrack.thirdparties;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

import moe.anitrack.thirdparties.common.ThirdpartyRequestsInterceptor;
import moe.anitrack.thirdparties.thirdparty.kitsu.KitsuConfiguration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("moe.anitrack.thirdparties.common")
@Import(KitsuConfiguration.class)
public class ThirdpartiesConfiguration {

    @Bean
    @Primary
    @Scope(SCOPE_PROTOTYPE)
    public RestTemplate defaultRestTemplate(final ThirdpartyRequestsInterceptor requestsInterceptor) {
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(requestsInterceptor);
        return restTemplate;
    }

}
