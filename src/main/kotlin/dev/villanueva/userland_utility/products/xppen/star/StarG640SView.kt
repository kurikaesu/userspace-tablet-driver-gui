package dev.villanueva.userland_utility.products.xppen.star

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class StarG640SView : ProductSingleSideShortcutsView() {
    private val myController: StarG640SController by inject()

    init {
        super.controller = myController
        setup()
    }
}
