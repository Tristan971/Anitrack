package moe.anitrack.thirdparties.thirdparty.kitsu;

import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class KitsuConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(KitsuConfiguration.class);

    private final KitsuApiHeaderInterceptor headerInterceptor;

    public KitsuConfiguration(KitsuApiHeaderInterceptor headerInterceptor) {
        this.headerInterceptor = headerInterceptor;
    }

    @Qualifier("kitsu")
    @Bean
    public RestTemplate kitsuRestTemplate(final Supplier<RestTemplate> baseRestTemplate, final KitsuApiHateoasInterceptor hateoasInterceptor) {
        LOGGER.info("Creating Kitsu-specific Content-Type/Accept headers interceptor.");
        final RestTemplate restTemplate = baseRestTemplate.get();
        restTemplate.getInterceptors().add(headerInterceptor);
        restTemplate.getInterceptors().add(hateoasInterceptor);
        return restTemplate;
    }

}
