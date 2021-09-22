package dev.villanueva.userland_utility.products

import dev.villanueva.userland_utility.products.xppen.DecoProController
import dev.villanueva.userland_utility.products.xppen.DecoProView
import kotlin.reflect.KClass

object SupportedProducts {
    private val productToClassMap: MutableMap<String, KClass<out ProductView>> = HashMap()
    private val nameToProductIdMap: MutableMap<String, String> = HashMap()
    private val productIdToName: MutableMap<String, String> = HashMap()

    init {
        productToClassMap[DecoProController.getProductName()] = DecoProView::class
        nameToProductIdMap[DecoProController.getProductName()] = DecoProController.getVendorProductString()
        productIdToName[DecoProController.getVendorProductString()] = DecoProController.getProductName()
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