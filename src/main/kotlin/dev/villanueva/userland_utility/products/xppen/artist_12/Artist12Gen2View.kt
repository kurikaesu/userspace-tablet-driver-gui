package dev.villanueva.userland_utility.products.xppen.artist_12

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class Artist12Gen2View : ProductSingleSideShortcutsView() {
    private val myController: Artist12Gen2Controller by inject()

    init {
        super.controller = myController
        setup()
    }
}