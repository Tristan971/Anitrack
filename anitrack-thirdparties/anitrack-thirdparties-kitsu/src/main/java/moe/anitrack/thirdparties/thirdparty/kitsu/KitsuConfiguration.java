package moe.anitrack.thirdparties.thirdparty.kitsu;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("Duplicates")
@Configuration
@PropertySource("classpath:kitsu.properties")
public class KitsuConfiguration {

    private final KitsuApiHeaderInterceptor headerInterceptor;
    private final KitsuApiHateoasInterceptor hateoasInterceptor;

    public KitsuConfiguration(
            KitsuApiHeaderInterceptor headerInterceptor,
            KitsuApiHateoasInterceptor hateoasInterceptor
    ) {
        this.headerInterceptor = headerInterceptor;
        this.hateoasInterceptor = hateoasInterceptor;
    }

    @Qualifier("kitsu")
    @Bean
    public RestTemplate kitsu(final Supplier<RestTemplate> baseRestTemplate) {
        final RestTemplate restTemplate = baseRestTemplate.get();
        restTemplate.getInterceptors().add(headerInterceptor);
        restTemplate.getInterceptors().add(hateoasInterceptor);
        return restTemplate;
    }

}
