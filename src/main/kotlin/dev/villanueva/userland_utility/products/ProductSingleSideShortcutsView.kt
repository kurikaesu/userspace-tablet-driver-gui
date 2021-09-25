package dev.villanueva.userland_utility.products

import javafx.scene.layout.VBox
import tornadofx.action
import tornadofx.button
import tornadofx.form
import tornadofx.useMaxWidth

open class ProductSingleSideShortcutsView : ProductView() {
    protected var controller: ProductController? = null

    override val root = VBox()

    fun setup() {
        with(root) {
            title = deviceName
            if (controller == null) {
                throw RuntimeException("Controller is null")
            }

            form {
                val itemIterator = controller!!.mapItems.listIterator()
                createFieldSetFromIterator(this, itemIterator, controller!!)
                button("Save/Submit Configuration") {
                    useMaxWidth = true
                    action {
                        controller!!.commitChanges(deviceConfiguration)
                    }
                }
            }
        }
    }
}