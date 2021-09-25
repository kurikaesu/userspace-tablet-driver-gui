package dev.villanueva.userland_utility.products.xppen.artist_24_pro

import dev.villanueva.userland_utility.products.DriverCodeIDs
import dev.villanueva.userland_utility.products.MappableItem
import dev.villanueva.userland_utility.products.MappableItemType
import dev.villanueva.userland_utility.products.ProductController
import dev.villanueva.userland_utility.products.config.Configuration
import dev.villanueva.userland_utility.products.config.DeviceConfiguration

class Artist24ProController : ProductController() {
    init {
        mapLeftItems.add(MappableItem(MappableItemType.Button, "K1", DriverCodeIDs.BTN_0.code, 0))
        mapLeftItems.add(MappableItem(MappableItemType.Button, "K2", DriverCodeIDs.BTN_1.code, 0))
        mapLeftItems.add(MappableItem(MappableItemType.Button, "K3", DriverCodeIDs.BTN_2.code, 0))
        mapLeftItems.add(MappableItem(MappableItemType.Button, "K4", DriverCodeIDs.BTN_3.code, 0))
        mapLeftItems.add(MappableItem(MappableItemType.Button, "K5", DriverCodeIDs.BTN_4.code, 0))
        mapLeftItems.add(MappableItem(MappableItemType.Dial, "Dial -> Left", DriverCodeIDs.REL_WHEEL.code, -1))
        mapLeftItems.add(MappableItem(MappableItemType.Dial, "Dial -> Right", DriverCodeIDs.REL_WHEEL.code, 1))
        mapLeftItems.add(MappableItem(MappableItemType.Button, "K6", DriverCodeIDs.BTN_5.code, 0))
        mapLeftItems.add(MappableItem(MappableItemType.Button, "K7", DriverCodeIDs.BTN_6.code, 0))
        mapLeftItems.add(MappableItem(MappableItemType.Button, "K8", DriverCodeIDs.BTN_7.code, 0))
        mapLeftItems.add(MappableItem(MappableItemType.Button, "K9", DriverCodeIDs.BTN_8.code, 0))
        mapLeftItems.add(MappableItem(MappableItemType.Button, "K10", DriverCodeIDs.BTN_9.code, 0))

        mapRightItems.add(MappableItem(MappableItemType.Button, "K11", DriverCodeIDs.BTN_SOUTH.code, 0))
        mapRightItems.add(MappableItem(MappableItemType.Button, "K12", DriverCodeIDs.BTN_EAST.code, 0))
        mapRightItems.add(MappableItem(MappableItemType.Button, "K13", DriverCodeIDs.BTN_C.code, 0))
        mapRightItems.add(MappableItem(MappableItemType.Button, "K14", DriverCodeIDs.BTN_NORTH.code, 0))
        mapRightItems.add(MappableItem(MappableItemType.Button, "K15", DriverCodeIDs.BTN_WEST.code, 0))
        mapRightItems.add(MappableItem(MappableItemType.Dial, "Dial -> Left", DriverCodeIDs.REL_HWHEEL.code, -1))
        mapRightItems.add(MappableItem(MappableItemType.Dial, "Dial -> Right", DriverCodeIDs.REL_HWHEEL.code, 1))
        mapRightItems.add(MappableItem(MappableItemType.Button, "K16", DriverCodeIDs.BTN_Z.code, 0))
        mapRightItems.add(MappableItem(MappableItemType.Button, "K17", DriverCodeIDs.BTN_TL.code, 0))
        mapRightItems.add(MappableItem(MappableItemType.Button, "K18", DriverCodeIDs.BTN_TR.code, 0))
        mapRightItems.add(MappableItem(MappableItemType.Button, "K19", DriverCodeIDs.BTN_TL2.code, 0))
        mapRightItems.add(MappableItem(MappableItemType.Button, "K20", DriverCodeIDs.BTN_TR2.code, 0))
    }

    override fun updateExistingDeviceConfig(deviceConfiguration: DeviceConfiguration): Configuration {
        val existingConfig = Configuration.readConfig()
        existingConfig.deviceConfigurations[getVendorIdAsString()]!![getProductIdAsString()] = deviceConfiguration
        return existingConfig
    }

    companion object {
        private fun getProductId(): Short {
            return 2349
        }

        private fun getVendorId(): Short {
            return 10429
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
            return "XP-Pen Artist 24 Pro"
        }
    }
}