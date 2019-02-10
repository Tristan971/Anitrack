package moe.anitrack.thirdparties.thirdparty.kitsu;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class KitsuAuthenticationServiceTest extends BaseKitsuTest {

    @Autowired
    private KitsuAuthenticationService sut;

    @Autowired
    private Environment environment;

    @Test
    public void authenticateWith() throws IOException {
        // given a valid username password combination
        final String validUserName = environment.getRequiredProperty("tests-kitsu-username");
        final String validPassword = environment.getRequiredProperty("tests-kitsu-password");

        // then when we call for login
        sut.authenticateWith(validUserName, validPassword);

        // we are authenticated
        assertThat(sut.getAuthentication()).isPresent();
    }

}
