package dev.villanueva.userland_utility.products

import javafx.scene.layout.VBox
import tornadofx.action
import tornadofx.button
import tornadofx.form
import tornadofx.hbox
import tornadofx.useMaxWidth
import tornadofx.vbox

open class ProductDoubleSideShortcutsView : ProductView() {
    protected var controller: ProductController? = null

    override val root = VBox()

    fun setup() {
        with(root) {
            title = deviceName

            form {
                hbox {
                    vbox {
                        val itemIterator = controller!!.mapLeftItems.listIterator()
                        createFieldSetFromIterator(this, itemIterator, controller!!)
                    }
                    vbox {
                        val itemIterator = controller!!.mapRightItems.listIterator()
                        createFieldSetFromIterator(this, itemIterator, controller!!)
                    }
                }
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