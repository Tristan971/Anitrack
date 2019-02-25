package moe.anitrack.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import moe.anitrack.core.thirdparties.CurrentThirdpartySystem;

@ContextConfiguration(classes = AnitrackConfiguration.class)
@RunWith(SpringRunner.class)
public class AnitrackConfigurationTest {

    @Autowired
    private CurrentThirdpartySystem currentThirdpartySystem;

    @Test
    public void contextLoads() {
        assertThat(currentThirdpartySystem).isNotNull();
    }

}
