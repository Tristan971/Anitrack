package moe.anitrack.server;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = ServerConfiguration.class)
@RunWith(SpringRunner.class)
public class ServerConfigurationTest {

    @Autowired
    private PlayerController playerController;

    @Test
    public void contextLoads() {
        assertThat(playerController).isNotNull();
    }

}
