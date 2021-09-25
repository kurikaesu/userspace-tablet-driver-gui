package dev.villanueva.userland_utility.products.huion.wh1409_2048

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class WH1409_2048View : ProductSingleSideShortcutsView() {
    private val myController: WH1409_2048Controller by inject()

    init {
        super.controller = myController
        setup()
    }
}
