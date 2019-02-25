package moe.anitrack.base;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration(classes = BaseConfiguration.class)
@RunWith(SpringRunner.class)
public class BaseConfigurationTest {

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.name}")
    private String appName;

    @Test
    public void contextLoads() {
        assertThat(appVersion).isNotBlank();
        assertThat(appName).isNotBlank();
    }

    @Test
    public void versionIsCorrectlyFiltered() {
        assertThat(appVersion).isNotEqualTo("@project.version@");
    }

}
