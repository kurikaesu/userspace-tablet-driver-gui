package dev.villanueva.userland_utility.products.xppen.artist_12_pro

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class Artist12ProView : ProductSingleSideShortcutsView() {
    private val myController: Artist12ProController by inject()

    init {
        super.controller = myController
        setup()
    }
}
