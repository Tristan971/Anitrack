package moe.tristan.kitsumonogatari.view;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import moe.tristan.easyfxml.EasyFxml;
import moe.tristan.easyfxml.api.FxmlNode;
import moe.tristan.easyfxml.spring.application.FxUiManager;

@Component
public class KitsuUiManager extends FxUiManager {

    private final Environment environment;

    protected KitsuUiManager(EasyFxml easyFxml, Environment environment) {
        super(easyFxml);
        this.environment = environment;
    }

    @Override
    protected String title() {
        return environment.getProperty("app.title");
    }

    @Override
    protected FxmlNode mainComponent() {
        return null;
    }

}
