package moe.anitrack.core.persistence;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@ComponentScan
@EnableJpaRepositories(
        basePackages = "moe.anitrack.core.persistence",
        entityManagerFactoryRef = "localH2EntityManagerFactory"
)
@PropertySource("classpath:persistence.properties")
public class PersistenceConfiguration {

    @Bean
    @ConfigurationProperties("local.persistence")
    public DataSource localPersistenceDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean localPersistenceManagerFactory(
            EntityManagerFactoryBuilder factoryBuilder,
            DataSource localPersistenceDataSource
    ) {
        return factoryBuilder
                .dataSource(localPersistenceDataSource)
                .packages("moe.anitrack.core.persistence")
                .persistenceUnit("localPersistence")
                .build();
    }

}
