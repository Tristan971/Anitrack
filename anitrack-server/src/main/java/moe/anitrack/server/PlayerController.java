package moe.anitrack.server;

import static moe.anitrack.server.Routes.PLAYER;
import static moe.anitrack.server.Routes.Player.INFO;
import static moe.anitrack.server.Routes.Player.PLAYED;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import moe.anitrack.server.objects.ImmutableInfoData;
import moe.anitrack.server.objects.InfoData;
import moe.anitrack.server.objects.PlayedMediaEvent;
import moe.anitrack.server.reactive.PlayedMediaEventDispatcher;

@RestController
@RequestMapping(PLAYER)
public class PlayerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);

    private final Environment environment;
    private final PlayedMediaEventDispatcher playedMediaEventDispatcher;

    @Autowired
    public PlayerController(Environment environment, PlayedMediaEventDispatcher playedMediaEventDispatcher) {
        this.environment = environment;
        this.playedMediaEventDispatcher = playedMediaEventDispatcher;
    }

    @GetMapping(INFO)
    public InfoData info() {
        return ImmutableInfoData
                .builder()
                .applicationVersion(environment.getRequiredProperty("app.version"))
                .build();
    }

    @PostMapping(PLAYED)
    public void played(@RequestBody final PlayedMediaEvent playedMediaEvent) {
        LOGGER.info("Got play notification : {}", playedMediaEvent);
        playedMediaEventDispatcher.eventReceived(playedMediaEvent);
    }

}
