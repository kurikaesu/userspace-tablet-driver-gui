package dev.villanueva.userland_utility.products.xppen.artist_16_pro

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class Artist16ProView : ProductSingleSideShortcutsView() {
    private val myController: Artist16ProController by inject()

    init {
        super.controller = myController
        setup()
    }
}
