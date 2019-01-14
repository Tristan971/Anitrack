package moe.tristan.kitsumonogatari.listen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("player")
public class PlayerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);

    @PostMapping("/played")
    public void played(@RequestBody final PlayedElementData playedElementData) {
        LOGGER.info("Got play notification : {}", playedElementData);
    }

}
