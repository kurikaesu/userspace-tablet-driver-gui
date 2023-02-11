package dev.villanueva.userland_utility.products.xppen.star

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class StarG430SView : ProductSingleSideShortcutsView() {
    private val myController: StarG430SController by inject()

    init {
        super.controller = myController
        setup()
    }
}