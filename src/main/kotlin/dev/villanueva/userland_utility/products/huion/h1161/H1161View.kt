package dev.villanueva.userland_utility.products.huion.h1161

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class H1161View : ProductSingleSideShortcutsView() {
    private val myController: H1161Controller by inject()

    init {
        super.controller = myController
        setup()
    }
}