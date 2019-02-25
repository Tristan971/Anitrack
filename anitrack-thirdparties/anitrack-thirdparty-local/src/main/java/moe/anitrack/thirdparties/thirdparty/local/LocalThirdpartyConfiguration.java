package moe.anitrack.thirdparties.thirdparty.local;

import static java.io.File.separator;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Lazy
@EnableAutoConfiguration
@EnableJpaRepositories
@Configuration
@ComponentScan
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@PropertySources({@PropertySource("classpath:local_h2.properties")})
public class LocalThirdpartyConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalThirdpartyConfiguration.class);

    private static final String H2_DB_URL_FORMAT = "jdbc:h2:${user.home}/.anitrack/%s;DB_CLOSE_ON_EXIST=FALSE";

    private final DataSourceProperties dbProps;
    private final String dbPropsLocation;

    public LocalThirdpartyConfiguration(DataSourceProperties dbProps, @Value("${db_filename}") String dbFilename) {
        this.dbProps = dbProps;
        this.dbPropsLocation = System.getProperty("user.home") + separator + ".anitrack" + separator + dbFilename;
    }

    @PostConstruct
    public void postConstruct() {
        LOGGER.info("Configuring H2 JDBC local database");
        setH2DbLocation();
    }

    void setH2DbLocation() {
        final String dbUrl = String.format(H2_DB_URL_FORMAT, dbPropsLocation);
        dbProps.setUrl(dbUrl);
    }

}

