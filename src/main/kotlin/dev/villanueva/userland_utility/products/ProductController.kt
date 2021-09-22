package dev.villanueva.userland_utility.products

import dev.villanueva.userland_utility.products.config.DeviceConfiguration
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.Controller

abstract class ProductController : Controller() {
    val mapItems: ObservableList<MappableItem> = FXCollections.observableArrayList()

    abstract fun updateMapping(
        itemType: MappableItemType,
        referenceId: Int,
        mappedValues: HashSet<Int>,
        matchValue: Int,
        mappedItemType: MappableItemType)

    abstract fun commitChanges(deviceConfiguration: DeviceConfiguration?)
}