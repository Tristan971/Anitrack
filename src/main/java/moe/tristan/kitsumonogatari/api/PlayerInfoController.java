package moe.tristan.kitsumonogatari.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import moe.tristan.kitsumonogatari.model.listeners.NotificationService;

@RestController
@RequestMapping("player")
public class PlayerInfoController {

    private final NotificationService notificationService;

    @Autowired
    public PlayerInfoController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("playedFile/{playerName}")
    public void pushPlayedFile(@PathVariable("playerName") final String playerName, @RequestBody final String title) {
        notificationService.notify(title);
    }

}
