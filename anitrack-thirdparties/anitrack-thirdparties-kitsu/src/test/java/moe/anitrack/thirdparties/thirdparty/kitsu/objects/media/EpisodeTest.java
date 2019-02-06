package moe.anitrack.thirdparties.thirdparty.kitsu.objects.media;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import moe.anitrack.thirdparties.common.ThirdpartiesConfiguration;

@ContextConfiguration(classes = ThirdpartiesConfiguration.class)
@RunWith(SpringRunner.class)
public class EpisodeTest {

    private static final String TEST_EPISODE_URL = "https://kitsu.io/api/edge/episodes/31";

    @Autowired
    @Qualifier("kitsu")
    private RestTemplate restTemplate;

    @Test
    public void deserializesProperly() {
        final ResponseEntity<ImmutableEpisode> response = restTemplate.exchange(
                TEST_EPISODE_URL,
                HttpMethod.GET,
                null,
                ImmutableEpisode.class
        );
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

        final Episode episode = response.getBody();
        assertThat(episode).isNotNull();
    }

}
