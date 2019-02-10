package moe.anitrack.thirdparties;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import moe.anitrack.thirdparties.common.ThirdpartyRequestsInterceptor;
import moe.anitrack.thirdparties.common.ThirdpartyService;
import moe.anitrack.thirdparties.thirdparty.kitsu.KitsuConfiguration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("moe.anitrack.thirdparties.common")
@Import(KitsuConfiguration.class)
public class ThirdpartiesConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThirdpartiesConfiguration.class);

    private final ThirdpartyRequestsInterceptor thirdpartyRequestsInterceptor;
    private final List<ThirdpartyService> thirdpartyServices;

    public ThirdpartiesConfiguration(
            ThirdpartyRequestsInterceptor thirdpartyRequestsInterceptor,
            List<ThirdpartyService> thirdpartyServices
    ) {
        this.thirdpartyRequestsInterceptor = thirdpartyRequestsInterceptor;
        this.thirdpartyServices = thirdpartyServices;
    }

    @PostConstruct
    public void onInitialization() {
        LOGGER.info("Constructed the following thirdparty services: {}", thirdpartyServices);
    }

    @Bean
    public Supplier<RestTemplate> baseRestTemplateSupplier() {
        return () -> {
            final RestTemplate restTemplate = new RestTemplate();
            restTemplate.getInterceptors().add(this.thirdpartyRequestsInterceptor);
            return restTemplate;
        };
    }

    @Primary
    @Bean
    public RestTemplate defaultRestTemplate() {
        return baseRestTemplateSupplier().get();
    }

}
