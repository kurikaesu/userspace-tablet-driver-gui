package dev.villanueva.userland_utility.products.huion.kamvas_pro_13

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class KamvasPro13View : ProductSingleSideShortcutsView() {
    private val myController: KamvasPro13Controller by inject()

    init {
        super.controller = myController
        setup()
    }
}