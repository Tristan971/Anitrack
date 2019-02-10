package moe.anitrack.thirdparties.thirdparty.kitsu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:kitsu.properties")
@ComponentScan("moe.anitrack.thirdparties.thirdparty.kitsu")
public class KitsuConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(KitsuConfiguration.class);

    private final KitsuApiHeaderInterceptor headerInterceptor;
    private final KitsuApiHateoasInterceptor hateoasInterceptor;
    private final KitsuAuthorizationInterceptor authorizationInterceptor;

    public KitsuConfiguration(
            KitsuApiHeaderInterceptor headerInterceptor,
            KitsuApiHateoasInterceptor hateoasInterceptor,
            KitsuAuthorizationInterceptor authorizationInterceptor
    ) {
        this.headerInterceptor = headerInterceptor;
        this.hateoasInterceptor = hateoasInterceptor;
        this.authorizationInterceptor = authorizationInterceptor;
        LOGGER.info("Kitsu configuration loaded.");
    }

    @Qualifier("kitsu")
    @Bean
    public RestTemplate kitsu(final RestTemplate restTemplate) {
        restTemplate.getInterceptors().add(headerInterceptor);
        restTemplate.getInterceptors().add(authorizationInterceptor);
        restTemplate.getInterceptors().add(hateoasInterceptor);
        return restTemplate;
    }

}
