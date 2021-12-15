package dev.villanueva.userland_utility.products.xppen.artist_15_6_pro

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class Artist15_6ProView : ProductSingleSideShortcutsView() {
    private val myController: Artist15_6ProController by inject()

    init {
        super.controller = myController
        setup()
    }
}
