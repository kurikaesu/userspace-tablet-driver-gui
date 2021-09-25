package dev.villanueva.userland_utility.products.xppen.deco_pro

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class DecoProMediumView : ProductSingleSideShortcutsView() {
    private val myController: DecoProMediumController by inject()

    init {
        super.controller = myController
        setup()
    }
}
