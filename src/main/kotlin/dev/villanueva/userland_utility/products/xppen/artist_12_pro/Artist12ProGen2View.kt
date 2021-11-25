package dev.villanueva.userland_utility.products.xppen.artist_12_pro

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView
import dev.villanueva.userland_utility.products.xppen.artist_12.Artist12Gen2Controller

class Artist12ProGen2View : ProductSingleSideShortcutsView() {
    private val myController: Artist12Gen2Controller by inject()

    init {
        super.controller = myController
        setup()
    }
}
