package moe.anitrack.server;

import static moe.anitrack.server.Routes.PLAYER;
import static moe.anitrack.server.Routes.Player.INFO;
import static moe.anitrack.server.Routes.Player.PLAYED;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import moe.anitrack.server.objects.ImmutableInfoData;
import moe.anitrack.server.objects.ImmutablePlayedMediaEvent;
import moe.anitrack.server.objects.InfoData;
import moe.anitrack.server.objects.PlayedMediaEvent;

@TestPropertySource("classpath:server.properties")
public class PlayerControllerTest extends BaseServerIntegrationTest {

    @Test
    public void info() {
        final var infoResponse = restTemplate().getForEntity(urlForRoute(PLAYER, INFO), ImmutableInfoData.class);
        assertThat(infoResponse.getStatusCode().is2xxSuccessful()).isTrue();

        final InfoData infoData = infoResponse.getBody();
        assertThat(infoData).isNotNull();

        final String currentAppVersion = environment().getProperty("app.version");
        assertThat(infoData.getApplicationVersion()).isEqualTo(currentAppVersion);
    }

    @Test
    public void played() {
        PlayedMediaEvent playedMediaEvent = ImmutablePlayedMediaEvent.builder().name("Media name").player("Player name").build();
        final ResponseEntity<Void> reseponse = restTemplate().postForEntity(urlForRoute(PLAYER, PLAYED), playedMediaEvent, Void.class);
        assertThat(reseponse.getStatusCode().is2xxSuccessful()).isTrue();
    }

}
