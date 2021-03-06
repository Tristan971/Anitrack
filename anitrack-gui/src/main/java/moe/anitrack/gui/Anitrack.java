package moe.anitrack.gui;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

import moe.anitrack.core.AnitrackConfiguration;
import moe.tristan.easyfxml.FxApplication;

@SpringBootApplication
@Import(AnitrackConfiguration.class)
public class Anitrack extends FxApplication {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected SpringApplicationBuilder getSab() {
        return super.getSab().web(WebApplicationType.SERVLET);
    }

}
