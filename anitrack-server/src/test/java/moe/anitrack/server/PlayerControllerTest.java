package moe.anitrack.server;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import moe.anitrack.server.objects.ImmutableInfoData;

@TestPropertySource("classpath:application.properties")
public class PlayerControllerTest extends BaseServerIntegrationTest {

    @Test
    public void info() {
        final var infoResponse = restTemplate().getForEntity("/info", ImmutableInfoData.class);
        assertThat(infoResponse.getStatusCode().is2xxSuccessful()).isTrue();
        environment().getProperty("app.version");
    }

    @Test
    public void played() {
    }

}
