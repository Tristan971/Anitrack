package moe.anitrack.core.persistence;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.Bindable;
import javax.persistence.metamodel.Metamodel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("moe.anitrack.core.persistence")
@EnableJpaRepositories("moe.anitrack.core.persistence")
@PropertySource("classpath:persistence.properties")
public class PersistenceConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceConfiguration.class);

    private final List<EntityManager> entityManagers;

    public PersistenceConfiguration(List<EntityManager> entityManagers) {
        this.entityManagers = entityManagers;
    }

    @PostConstruct
    public void afterSetUp() {
        LOGGER.info(
                "Persisted entities {}",
                entityManagers.stream()
                              .map(EntityManager::getMetamodel)
                              .map(Metamodel::getEntities)
                              .flatMap(Set::stream)
                              .map(Bindable::getBindableJavaType)
                              .map(Class::getSimpleName)
                              .collect(Collectors.toSet())
        );
    }

}
