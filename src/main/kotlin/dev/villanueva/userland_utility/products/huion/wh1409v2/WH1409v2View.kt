package dev.villanueva.userland_utility.products.huion.wh1409v2

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class WH1409v2View : ProductSingleSideShortcutsView() {
    private val myController: WH1409v2Controller by inject()

    init {
        super.controller = myController
        setup()
    }
}