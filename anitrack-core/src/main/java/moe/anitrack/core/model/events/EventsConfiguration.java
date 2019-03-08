package moe.anitrack.core.model.events;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class EventsConfiguration {

    @Bean
    public ApplicationEventMulticaster eventMulticaster() {
        return new SimpleApplicationEventMulticaster();
    }

}
