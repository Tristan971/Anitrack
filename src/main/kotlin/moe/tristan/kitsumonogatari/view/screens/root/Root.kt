package moe.tristan.kitsumonogatari.view.screens.root

import moe.tristan.easyfxml.api.FxmlController
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class Root : FxmlController {

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(Root::class.java)
    }

    override fun initialize() {
        LOGGER.debug("Initialized Root controller")
    }

}
