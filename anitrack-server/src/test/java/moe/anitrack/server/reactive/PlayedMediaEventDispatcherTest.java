package moe.anitrack.server.reactive;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import moe.anitrack.server.objects.ImmutablePlayedMediaEvent;
import moe.anitrack.server.objects.PlayedMediaEvent;
import moe.anitrack.server.reactive.bindings.PlayedMediaEventListener;

public class PlayedMediaEventDispatcherTest {

    private static final PlayedMediaEvent EVT_1 = ImmutablePlayedMediaEvent
            .builder()
            .name("EVT_1_NAME")
            .player("EVT_1_PLAYER")
            .build();

    private static final PlayedMediaEvent EVT_2 = ImmutablePlayedMediaEvent
            .builder()
            .name("EVT_2_NAME")
            .player("EVT_2_PLAYER")
            .build();

    private PlayedMediaEventDispatcher sut;

    @Before
    public void setUp() {
        sut = new PlayedMediaEventDispatcher();
    }

    @Test
    public void nonNullApi() {
        Stream.of(
                (Consumer<PlayedMediaEventListener>) sut::subscribe,
                (Consumer<PlayedMediaEvent>) sut::eventReceived
        ).forEach(
                consumer -> assertThatThrownBy(() -> consumer.accept(null)).isInstanceOf(NullPointerException.class)
        );
    }

    @Test
    public void cannotSubscribeTwiceWithSameListener() {
        final String someListenerName = "someListenerName";
        final PlayedMediaEventListener listener = mockListener(someListenerName);
        sut.subscribe(listener);
        assertThatThrownBy(() -> sut.subscribe(listener))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(someListenerName);
    }

    @Test
    public void supportsZeroListeners() {
        pushAndCheckReceptionOfEventsForListeners(List.of(EVT_1), Collections.emptyList());
    }

    @Test
    public void supportsOneListener() {
        pushAndCheckReceptionOfEventsForListeners(List.of(EVT_1, EVT_2), List.of(mockListener("SingleListener")));
    }

    @Test
    public void supportsManyListeners() {
        pushAndCheckReceptionOfEventsForListeners(List.of(EVT_1, EVT_2), List.of(mockListener("Listener1"), mockListener("Listener2")));
    }

    private void pushAndCheckReceptionOfEventsForListeners(final List<PlayedMediaEvent> events, final List<PlayedMediaEventListener> listeners) {
        listeners.forEach(listener -> {
            sut.subscribe(listener);
            verify(listener, never()).onPlayedEvent(any());
        });

        IntStream.range(0, events.size()).forEach(eventNumber -> {
            PlayedMediaEvent event = events.get(eventNumber);

            sut.eventReceived(event);

            listeners.forEach(listener -> {
                verify(listener, timeout(2000).times(1)).onPlayedEvent(eq(event)); // only received this event once
                verify(listener, timeout(2000).times(eventNumber + 1)).onPlayedEvent(any()); // received all previous events + 1 (this one)
            });
        });
    }

    private static PlayedMediaEventListener mockListener(String name) {
        return mock(PlayedMediaEventListener.class, name);
    }

}
