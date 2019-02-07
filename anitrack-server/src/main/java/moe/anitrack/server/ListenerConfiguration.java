package moe.anitrack.server;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@ComponentScan("moe.anitrack.server")
public class ListenerConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListenerConfiguration.class);

    private final Map<String, Object> anitrackRestControllers;
    private final List<RequestMappingHandlerMapping> loadedMappings;

    public ListenerConfiguration(
            final ConfigurableApplicationContext applicationContext,
            final List<RequestMappingHandlerMapping> loadedMappings
    ) {
        this.anitrackRestControllers = applicationContext.getBeansWithAnnotation(RestController.class);
        this.loadedMappings = loadedMappings;
    }

    @PostConstruct
    public void onInitialization() {
        final String mappings = loadedMappings
                .stream()
                .map(RequestMappingHandlerMapping::getHandlerMethods)
                .map(Map::entrySet)
                .flatMap(Set::stream)
                .filter(entry -> entry.getValue().getBeanType().getName().contains(getClass().getPackageName()))
                .map(entry -> "\t" + MappingUtils.toString(entry.getKey(), entry.getValue()))
                .collect(Collectors.joining("\n"));
        LOGGER.info(
                "Local Anitrack server loaded the following controllers: {}\nAnd following endpoints:\n{}",
                anitrackRestControllers,
                mappings
        );
    }

}
