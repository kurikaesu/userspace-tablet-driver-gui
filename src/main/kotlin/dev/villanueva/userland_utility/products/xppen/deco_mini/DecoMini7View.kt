package dev.villanueva.userland_utility.products.xppen.deco_mini

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class DecoMini7View : ProductSingleSideShortcutsView() {
    private val myController: DecoMini7Controller by inject()

    init {
        super.controller = myController
        setup()
    }
}