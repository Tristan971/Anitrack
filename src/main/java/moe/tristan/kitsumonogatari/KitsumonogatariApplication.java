package moe.tristan.kitsumonogatari;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import moe.tristan.easyfxml.spring.application.FxSpringApplication;
import moe.tristan.easyfxml.spring.application.FxSpringContext;

@SpringBootApplication
@Import(FxSpringContext.class)
public class KitsumonogatariApplication extends FxSpringApplication {

    public static void main(String[] args) {
        launch(args);
    }

}
