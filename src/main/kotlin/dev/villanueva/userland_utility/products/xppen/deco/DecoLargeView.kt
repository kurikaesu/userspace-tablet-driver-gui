package dev.villanueva.userland_utility.products.xppen.deco

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class DecoLargeView : ProductSingleSideShortcutsView() {
    private val myController: DecoLargeController by inject()

    init {
        super.controller = myController
        setup()
    }
}