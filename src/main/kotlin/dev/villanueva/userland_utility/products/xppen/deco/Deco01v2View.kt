package dev.villanueva.userland_utility.products.xppen.deco

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class Deco01v2View : ProductSingleSideShortcutsView() {
    private val myController: Deco01v2Controller by inject()

    init {
        super.controller = myController
        setup()
    }
}