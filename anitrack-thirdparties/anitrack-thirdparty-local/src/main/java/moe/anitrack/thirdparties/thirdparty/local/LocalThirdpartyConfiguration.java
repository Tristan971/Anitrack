package moe.anitrack.thirdparties.thirdparty.local;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
@EnableAutoConfiguration
@EnableJpaRepositories(
        basePackages = "moe.anitrack.thirdparties.thirdparty.local",
        entityManagerFactoryRef = "localThirdpartyH2DbEntityManagerFactory"
)
@PropertySource("classpath:local_thirdparty.properties")
public class LocalThirdpartyConfiguration {

    @Bean
    @ConfigurationProperties("local.thirdparty.h2db")
    public DataSource localThirdpartyH2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean localThirdpartyH2EntityManagerFactory(
            EntityManagerFactoryBuilder factoryBuilder,
            DataSource localThirdpartyH2DataSource
    ) {
        return factoryBuilder
                .dataSource(localThirdpartyH2DataSource)
                .packages("moe.anitrack.thirdparties.thirdparty.local")
                .persistenceUnit("localThirdpartyH2")
                .build();
    }

}

