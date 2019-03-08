package moe.anitrack.core;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import moe.anitrack.base.BaseConfiguration;
import moe.anitrack.core.model.events.EventsConfiguration;
import moe.anitrack.core.model.thirdparties.ThirdpartiesConfiguration;
import moe.anitrack.persistence.PersistenceConfiguration;
import moe.anitrack.server.ServerConfiguration;

@Configuration
@Import(value = {
        BaseConfiguration.class,
        EventsConfiguration.class,
        PersistenceConfiguration.class,
        ServerConfiguration.class,
        ThirdpartiesConfiguration.class
})
public class AnitrackConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnitrackConfiguration.class);

    public AnitrackConfiguration() {
    }

    @PostConstruct
    public void onInitialization() {
        LOGGER.info("Anitrack core configuration loaded.");
    }

}
