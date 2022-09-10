package dev.villanueva.userland_utility.products.xppen.artist_pro_16

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class ArtistPro16View : ProductSingleSideShortcutsView() {
    private val myController: ArtistPro16Controller by inject()

    init {
        super.controller = myController
        setup()
    }
}
