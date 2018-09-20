package moe.tristan.kitsumonogatari.thirdparties;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import moe.tristan.kitsumonogatari.model.media.Media;

import io.vavr.control.Try;

@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class TrackingServiceTestBase {

    private static final String EXISTING = "Bakemonogatari";
    private static final String NON_EXISTING = "FAKE_NAME_NO_SHOW_SHOULD_BE_NAMED_THIS_WAY_COME_ON";

    private final Class<? extends TrackingService> trackingApiClass;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApplicationContext context;

    private TrackingService sut;

    protected TrackingServiceTestBase(Class<? extends TrackingService> trackingApiClass) {
        this.trackingApiClass = trackingApiClass;
    }

    @PostConstruct
    public void setupApi() {
        sut = context.getBean(trackingApiClass);
    }

    @Test
    public void testFindExistingMedia() {
        // given an api
        // when searching for an existing media
        Try<Media> found = sut.searchMedia(EXISTING);

        // we find it
        assertThat(found.isSuccess()).isTrue();

        // and it is what we expect
        assertThat(found.map(Media::getTitle).toJavaOptional()).contains(EXISTING);
    }

    @Test
    public void testNotFoundNonexistingMedia() {
        //given an api
        // when searching for a non existing media
        Try<Media> notFound = sut.searchMedia(NON_EXISTING);

        // we do not find it
        assertThat(notFound.isFailure()).isTrue();

        // and its failure class is IllegalArgumentException
        assertThat(notFound.getCause()).isInstanceOf(IllegalArgumentException.class);
    }

}
