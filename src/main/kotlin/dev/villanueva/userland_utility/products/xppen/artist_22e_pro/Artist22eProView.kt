package dev.villanueva.userland_utility.products.xppen.artist_22e_pro

import dev.villanueva.userland_utility.products.ProductDoubleSideShortcutsView

class Artist22eProView : ProductDoubleSideShortcutsView() {
    private val myController: Artist22eProController by inject()

    init {
        super.controller = myController
        setup()
    }
}
