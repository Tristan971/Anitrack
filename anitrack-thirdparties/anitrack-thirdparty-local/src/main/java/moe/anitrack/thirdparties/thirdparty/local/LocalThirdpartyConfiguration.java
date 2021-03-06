package moe.anitrack.thirdparties.thirdparty.local;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import moe.anitrack.persistence.PersistenceConfiguration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories("moe.anitrack.thirdparties.thirdparty.local")
@Import(PersistenceConfiguration.class)
public class LocalThirdpartyConfiguration {

}
