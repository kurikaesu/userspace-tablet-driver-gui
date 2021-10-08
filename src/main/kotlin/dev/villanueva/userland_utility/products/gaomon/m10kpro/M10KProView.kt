package dev.villanueva.userland_utility.products.gaomon.m10kpro

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class M10KProView : ProductSingleSideShortcutsView() {
    private val myController: M10KProController by inject()

    init {
        super.controller = myController
        setup()
    }
}