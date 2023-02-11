package dev.villanueva.userland_utility.products.xppen.star

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class StarG640View : ProductSingleSideShortcutsView() {
    private val myController: StarG640Controller by inject()

    init {
        super.controller = myController
        setup()
    }
}