package moe.anitrack.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import moe.anitrack.server.ListenerConfiguration;
import moe.anitrack.thirdparties.common.ThirdpartiesConfiguration;

@Configuration
@ComponentScan("moe.anitrack.core")
@Import({ListenerConfiguration.class, ThirdpartiesConfiguration.class})
public class AnitrackConfiguration {

}
