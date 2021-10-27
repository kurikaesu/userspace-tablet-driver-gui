package dev.villanueva.userland_utility.products.huion.kd100

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class KD100View : ProductSingleSideShortcutsView() {
    private val myController: KD100Controller by inject()

    init {
        super.controller = myController
        setup()
    }
}