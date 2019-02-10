package moe.anitrack.thirdparties.thirdparty.kitsu;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import moe.anitrack.thirdparties.common.ThirdpartiesConfiguration;

@ContextConfiguration(classes = ThirdpartiesConfiguration.class)
@RunWith(SpringRunner.class)
public class KitsuAuthenticationServiceTest {

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
