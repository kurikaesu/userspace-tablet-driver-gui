package dev.villanueva.userland_utility.products.xppen.artist_13_3_pro

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class Artist13_3ProView : ProductSingleSideShortcutsView() {
    private val myController: Artist13_3ProController by inject()

    init {
        super.controller = myController
        setup()
    }
}
