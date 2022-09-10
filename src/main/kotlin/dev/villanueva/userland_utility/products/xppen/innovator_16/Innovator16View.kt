package dev.villanueva.userland_utility.products.xppen.innovator_16

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class Innovator16View : ProductSingleSideShortcutsView() {
    private val myController: Innovator16Controller by inject()

    init {
        super.controller = myController
        setup()
    }
}
