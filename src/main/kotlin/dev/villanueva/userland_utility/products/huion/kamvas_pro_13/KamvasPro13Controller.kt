package dev.villanueva.userland_utility.products.huion.kamvas_pro_13

import dev.villanueva.userland_utility.products.DriverCodeIDs
import dev.villanueva.userland_utility.products.MappableItem
import dev.villanueva.userland_utility.products.MappableItemType
import dev.villanueva.userland_utility.products.ProductController
import dev.villanueva.userland_utility.products.config.Configuration
import dev.villanueva.userland_utility.products.config.DeviceConfiguration

class KamvasPro13Controller : ProductController() {
    init {
        mapItems.add(MappableItem(MappableItemType.StylusButton, "Stylus 1", DriverCodeIDs.BTN_STYLUS.code, 0))
        mapItems.add(MappableItem(MappableItemType.StylusButton, "Stylus 2", DriverCodeIDs.BTN_STYLUS2.code, 0))
        mapItems.add(MappableItem(MappableItemType.Button, "K1", DriverCodeIDs.BTN_0.code, 0))
        mapItems.add(MappableItem(MappableItemType.Button, "K2", DriverCodeIDs.BTN_1.code, 0))
        mapItems.add(MappableItem(MappableItemType.Dial, "Touch -> Up", DriverCodeIDs.REL_WHEEL.code, -1))
        mapItems.add(MappableItem(MappableItemType.Dial, "Touch -> Down", DriverCodeIDs.REL_WHEEL.code, 1))
        mapItems.add(MappableItem(MappableItemType.Button, "K3", DriverCodeIDs.BTN_2.code, 0))
        mapItems.add(MappableItem(MappableItemType.Button, "K4", DriverCodeIDs.BTN_3.code, 0))
        mapItems.add(MappableItem(MappableItemType.Button, "K5", DriverCodeIDs.BTN_4.code, 0))
    }

    override fun updateExistingDeviceConfig(deviceConfiguration: DeviceConfiguration): Configuration {
        val existingConfig = Configuration.readConfig()
        existingConfig.deviceConfigurations[getVendorIdAsString()]!![getProductIdAsString()] = deviceConfiguration
        return existingConfig
    }

    companion object {
        private fun getProductId(): Short {
            return 386
        }

        private fun getVendorId(): Short {
            return 9580
        }

        fun getProductIdAsString(): String {
            return getProductId().toString()
        }

        fun getVendorIdAsString(): String {
            return getVendorId().toString()
        }

        fun getVendorProductString(): String {
            return "${getVendorId()}:${getProductId()}"
        }

        fun getProductName(): String {
            return "Huion Kamvas Pro 13"
        }
    }
}