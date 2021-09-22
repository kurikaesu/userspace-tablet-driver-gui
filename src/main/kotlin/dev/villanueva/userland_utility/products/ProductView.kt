package dev.villanueva.userland_utility.products

import javafx.scene.Parent
import tornadofx.View

open class ProductView: View() {
    override val root: Parent
        get() = throw NotImplementedError("This class should not be called directly")
}
