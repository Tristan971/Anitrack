package moe.tristan.kitsumonogatari.model.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PlayerUpdateHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerUpdateHandler.class);

    public void handle(final PlayerUpdate playerUpdate) {
        LOGGER.debug("Received player update : {}", playerUpdate);
    }

}
