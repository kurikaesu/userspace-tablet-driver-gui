package dev.villanueva.userland_utility.products.xppen.artist_24_pro

import dev.villanueva.userland_utility.products.ProductDoubleSideShortcutsView

class Artist24ProView : ProductDoubleSideShortcutsView() {
    private val myController: Artist24ProController by inject()

    init {
        super.controller = myController
        setup()
    }
}