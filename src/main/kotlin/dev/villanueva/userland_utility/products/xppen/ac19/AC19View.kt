package dev.villanueva.userland_utility.products.xppen.ac19

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class AC19View : ProductSingleSideShortcutsView() {
    private val myController: AC19Controller by inject()

    init {
        super.controller = myController
        setup()
    }
}