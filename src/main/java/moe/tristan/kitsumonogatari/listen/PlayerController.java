package moe.tristan.kitsumonogatari.listen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "player")
public class PlayerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);

    private final Environment environment;

    @Autowired
    public PlayerController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/info")
    public InfoData info() {
        return ImmutableInfoData
                .builder()
                .applicationVersion(environment.getRequiredProperty("app.version"))
                .build();
    }

    @PostMapping("/played")
    public void played(@RequestBody final PlayedElementData playedElementData) {
        LOGGER.info("Got play notification : {}", playedElementData);
    }

}
