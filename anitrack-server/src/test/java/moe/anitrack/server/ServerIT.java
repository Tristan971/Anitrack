package moe.anitrack.server;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ServerIT extends BaseServerIntegrationTest {

    @Autowired
    private PlayerController playerController;

    @Test
    public void contextLoads() {
        assertThat(playerController).isNotNull();
    }

}
