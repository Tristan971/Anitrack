package moe.tristan.kitsumonogatari

import moe.tristan.easyfxml.spring.application.FxSpringApplication
import moe.tristan.easyfxml.spring.application.FxSpringContext
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Import
import java.awt.Toolkit

@SpringBootApplication
@EnableCaching
@Import(FxSpringContext::class)
class KitsumonogatariApplication: FxSpringApplication() {

    companion object {
        fun main(args: Array<String>) {
            Toolkit.getDefaultToolkit()
            launch(KitsumonogatariApplication::class.java)
        }
    }

}
