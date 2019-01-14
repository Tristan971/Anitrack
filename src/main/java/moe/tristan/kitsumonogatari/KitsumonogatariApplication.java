package moe.tristan.kitsumonogatari;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

import moe.tristan.easyfxml.EasyFxmlAutoConfiguration;
import moe.tristan.easyfxml.FxApplication;


@SpringBootApplication
@Import(EasyFxmlAutoConfiguration.class)
public class KitsumonogatariApplication extends FxApplication {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected SpringApplicationBuilder getSab() {
        return super.getSab().web(WebApplicationType.SERVLET);
    }

}
