package moe.tristan.kitsumonogatari.model.thirdparties.kitsu.objects.media;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import moe.tristan.kitsumonogatari.model.thirdparties.ApiTest;
import moe.tristan.kitsumonogatari.model.thirdparties.kitsu.ImmutableKitsuHateoasReply;
import moe.tristan.kitsumonogatari.model.thirdparties.kitsu.KitsuHateoasReply;

@SuppressWarnings("unchecked")
public class EpisodeTest extends ApiTest<KitsuHateoasReply<Episode>> {

    @Override
    public ParameterizedTypeReference<ImmutableKitsuHateoasReply<ImmutableEpisode>> getApiType() {
        return new ParameterizedTypeReference<>() {
        };
    }

    @Override
    public RequestEntity<KitsuHateoasReply<Episode>> getRequestEntity() {
        return new RequestEntity<>(HttpMethod.GET, URI.create("https://kitsu.io/api/edge/episodes/31"));
    }

    @Override
    public void testMatches() {
        final ResponseEntity<KitsuHateoasReply<Episode>> apiEpisode = fetchInstance();
        assertThat(apiEpisode.getStatusCode().is2xxSuccessful()).isTrue();

        final Episode episode = apiEpisode.getBody().getElement();
        logger().info("Response was : ", episode);
    }

    @Override
    public String restTemplateQualifier() {
        return "kitsu";
    }

}