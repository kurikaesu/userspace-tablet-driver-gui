package dev.villanueva.userland_utility.products

import dev.villanueva.userland_utility.products.xppen.deco_pro.DecoProSmallController
import dev.villanueva.userland_utility.products.xppen.deco_pro.DecoProSmallView
import kotlin.reflect.KClass

object SupportedProducts {
    private val productToClassMap: MutableMap<String, KClass<out ProductView>> = HashMap()
    private val nameToProductIdMap: MutableMap<String, String> = HashMap()
    private val productIdToName: MutableMap<String, String> = HashMap()

    init {
        productToClassMap[DecoProSmallController.getProductName()] = DecoProSmallView::class
        nameToProductIdMap[DecoProSmallController.getProductName()] = DecoProSmallController.getVendorProductString()
        productIdToName[DecoProSmallController.getVendorProductString()] = DecoProSmallController.getProductName()
    }

    fun getViewForProduct(product: String): KClass<out ProductView>? {
        return productToClassMap[product]
    }

    fun getProductIdFromName(name: String): String {
        return nameToProductIdMap[name] ?: "0000:0000"
    }

    fun getNameOfProduct(vendorId: Short, productId: Short): String {
        return productIdToName["$vendorId:$productId"] ?: "Unknown device"
    }
}