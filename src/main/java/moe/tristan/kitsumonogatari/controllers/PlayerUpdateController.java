package moe.tristan.kitsumonogatari.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import moe.tristan.kitsumonogatari.model.player.PlayerUpdate;
import moe.tristan.kitsumonogatari.model.player.PlayerUpdateHandler;

@RestController
@RequestMapping("player")
public class PlayerUpdateController {

    private final PlayerUpdateHandler playerUpdateHandler;

    @Autowired
    public PlayerUpdateController(PlayerUpdateHandler playerUpdateHandler) {
        this.playerUpdateHandler = playerUpdateHandler;
    }

    @PostMapping("playedFile")
    public void pushPlayedFile(@RequestBody final PlayerUpdate playerUpdate) {
        playerUpdateHandler.handle(playerUpdate);
    }

}
