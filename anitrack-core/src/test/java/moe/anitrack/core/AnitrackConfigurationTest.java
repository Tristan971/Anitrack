package moe.anitrack.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import moe.anitrack.core.thirdparties.choice.ThirdpartySelectionService;

@ContextConfiguration(classes = AnitrackConfiguration.class)
@RunWith(SpringRunner.class)
public class AnitrackConfigurationTest {

    @Autowired
    private ThirdpartySelectionService thirdpartySelectionService;

    @Test
    public void contextLoads() {
        assertThat(thirdpartySelectionService).isNotNull();
    }

}
