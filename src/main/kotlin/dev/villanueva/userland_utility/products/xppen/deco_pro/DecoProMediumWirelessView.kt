package dev.villanueva.userland_utility.products.xppen.deco_pro

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView
import tornadofx.action
import tornadofx.button
import tornadofx.form
import tornadofx.useMaxWidth

class DecoProMediumWirelessView : ProductSingleSideShortcutsView() {
    private val myController: DecoProMediumWirelessController by inject()

    init {
        super.controller = myController
        setup()

        with(root) {
            form {
                button("Set touch pad to mouse") {
                    useMaxWidth = true
                    action {
                        myController.setTouchPadToSetting(1)
                    }
                }

                button("Set touch pad to scroller") {
                    useMaxWidth = true
                    action {
                        myController.setTouchPadToSetting(2)
                    }
                }

                button("Set touch pad to customizable") {
                    useMaxWidth = true
                    action {
                        myController.setTouchPadToSetting(3)
                    }
                }
            }
        }
    }
}
