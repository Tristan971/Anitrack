package moe.anitrack.core.thirdparties;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import moe.anitrack.thirdparties.common.ThirdpartyService;
import moe.anitrack.thirdparties.thirdparty.kitsu.KitsuConfiguration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("moe.anitrack.thirdparties")
@Import(KitsuConfiguration.class)
public class ThirdpartiesConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThirdpartiesConfiguration.class);

    private final List<ThirdpartyService> thirdpartyServices;

    @Autowired
    public ThirdpartiesConfiguration(List<ThirdpartyService> thirdpartyServices) {
        this.thirdpartyServices = thirdpartyServices;
    }

    @PostConstruct
    public void postLoad() {
        final List<String> loadedServices = thirdpartyServices.stream().map(Object::getClass).map(Class::getSimpleName).collect(Collectors.toList());
        LOGGER.info("Loaded thirdparty services : {}", loadedServices);
    }

}
