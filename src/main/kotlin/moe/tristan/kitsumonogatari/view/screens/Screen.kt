package moe.tristan.kitsumonogatari.view.screens

import moe.tristan.easyfxml.api.FxmlController
import moe.tristan.easyfxml.api.FxmlFile
import moe.tristan.easyfxml.api.FxmlNode
import moe.tristan.kitsumonogatari.view.screens.root.Root

enum class Screen(
        private val filePath: String,
        private val controllerClazz: Class<out FxmlController>
) : FxmlNode {

    ROOT("root/Root.fxml", Root::class.java);

    companion object {
        const val FXML_VIEWS_ROOT: String = "moe/tristan/kitsumonogatari/view/screens/"
    }

    override fun getFile(): FxmlFile {
        return FxmlFile { FXML_VIEWS_ROOT + filePath }
    }

    override fun getControllerClass(): Class<out FxmlController> = controllerClazz
}
