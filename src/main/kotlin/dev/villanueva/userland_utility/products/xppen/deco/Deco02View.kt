package dev.villanueva.userland_utility.products.xppen.deco

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class Deco02View : ProductSingleSideShortcutsView() {
    private val myController: Deco02Controller by inject()

    init {
        super.controller = myController
        setup()
    }
}