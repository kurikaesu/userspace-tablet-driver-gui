package dev.villanueva.userland_utility.products.xppen.artist_22r_pro

import dev.villanueva.userland_utility.products.ProductDoubleSideShortcutsView

class Artist22rProView : ProductDoubleSideShortcutsView() {
    private val myController: Artist22rProController by inject()

    init {
        super.controller = myController
        setup()
    }
}