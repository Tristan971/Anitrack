package moe.anitrack.server;

import static java.lang.String.join;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = ServerConfiguration.class)
@RunWith(SpringRunner.class)
public abstract class BaseServerIntegrationTest {

    @LocalServerPort
    private int currentPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private Environment environment;

    public TestRestTemplate restTemplate() {
        return testRestTemplate;
    }

    public Environment environment() {
        return environment;
    }

    public String urlForPath(final String path) {
        return "http://localhost:" + currentPort + "/" + (path.startsWith("/") ? path.substring(1) : path);
    }

    public String urlForRoute(final String... fragments) {
        return urlForPath(join("", fragments));
    }

}
