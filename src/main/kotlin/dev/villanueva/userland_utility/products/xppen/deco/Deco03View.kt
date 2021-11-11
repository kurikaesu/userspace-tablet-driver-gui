package dev.villanueva.userland_utility.products.xppen.deco

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class Deco03View : ProductSingleSideShortcutsView() {
    private val myController: Deco03Controller by inject()

    init {
        super.controller = myController
        setup()
    }
}