package moe.anitrack.server;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@ComponentScan
@PropertySource("classpath:anitrack-server.properties")
public class ServerConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerConfiguration.class);

    private final Map<String, Object> anitrackRestControllers;

    public ServerConfiguration(final ConfigurableApplicationContext applicationContext) {
        this.anitrackRestControllers = applicationContext.getBeansWithAnnotation(RestController.class);
    }

    @PostConstruct
    public void onInitialization() {
        LOGGER.info("Local Anitrack server loaded the following controllers: {}", anitrackRestControllers);
    }

}
