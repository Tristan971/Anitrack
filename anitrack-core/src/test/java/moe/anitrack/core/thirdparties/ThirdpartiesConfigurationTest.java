package moe.anitrack.core.thirdparties;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import moe.anitrack.core.AnitrackConfiguration;
import moe.anitrack.thirdparties.common.ThirdpartyService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AnitrackConfiguration.class)
@DirtiesContext
public class ThirdpartiesConfigurationTest {

    @Autowired
    private List<ThirdpartyService> thirdpartyServices;

    @Test
    public void contextLoadsAndThirdpartiesDiscovered() {
        assertThat(thirdpartyServices)
                .isNotNull()
                .isNotEmpty()
                .hasSize(2);
    }

}
