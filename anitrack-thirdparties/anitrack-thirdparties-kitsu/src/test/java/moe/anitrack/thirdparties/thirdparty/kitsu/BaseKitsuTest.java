package moe.anitrack.thirdparties.thirdparty.kitsu;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import moe.anitrack.thirdparties.common.ThirdpartyBaseConfiguration;

@ContextConfiguration(classes = {
        ThirdpartyBaseConfiguration.class,
        KitsuConfiguration.class
})
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:private-kitsu.properties")
public abstract class BaseKitsuTest {

}
