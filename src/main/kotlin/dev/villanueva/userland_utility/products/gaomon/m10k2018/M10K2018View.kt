package dev.villanueva.userland_utility.products.gaomon.m10kpro

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class M10K2018View : ProductSingleSideShortcutsView() {
    private val myController: M10K2018Controller by inject()

    init {
        super.controller = myController
        setup()
    }
}