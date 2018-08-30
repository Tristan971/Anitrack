package moe.tristan.kitsumonogatari.view

import moe.tristan.easyfxml.EasyFxml
import moe.tristan.easyfxml.api.FxmlNode
import moe.tristan.easyfxml.spring.application.FxUiManager
import moe.tristan.kitsumonogatari.view.screens.Screen
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

@Component
class KitsumonogatariUiManager(easyFxml: EasyFxml, val environment: Environment) : FxUiManager(easyFxml) {

    override fun mainComponent(): FxmlNode = Screen.ROOT

    override fun title(): String {
        val appName: String = environment.getRequiredProperty("app.name", String::class.java)
        val appVersion: String = environment.getRequiredProperty("app.version", String::class.java)
        return "$appName [$appVersion]"
    }

}
