package dev.villanueva.userland_utility.products.xppen.deco

import dev.villanueva.userland_utility.iterop.DriverPackets
import dev.villanueva.userland_utility.iterop.DriverSocket
import dev.villanueva.userland_utility.products.MappableItemType
import dev.villanueva.userland_utility.products.ProductController
import dev.villanueva.userland_utility.products.config.Configuration
import dev.villanueva.userland_utility.products.config.DeviceConfiguration

class Deco01v2Controller : ProductController() {
    private val buttonBindings: HashMap<String, HashMap<String, HashSet<Int>>> = HashMap()

    override fun updateMapping(itemType: MappableItemType, referenceId: Int, mappedValues: HashSet<Int>, matchValue: Int, mappedItemType: MappableItemType) {
        val referenceIdString = referenceId.toString()
        val mappedItemTypeString = mappedItemType.value.toString()
        if (itemType == MappableItemType.Button) {
            if (!buttonBindings.containsKey(referenceIdString)) {
                buttonBindings[referenceIdString] = HashMap()
            }

            buttonBindings[referenceIdString]!![mappedItemTypeString] = HashSet(mappedValues)
        }
    }

    override fun commitChanges(deviceConfiguration: DeviceConfiguration?) {
        println("Committing changes")
        if (deviceConfiguration != null) {
            deviceConfiguration.mapping.buttons = this.buttonBindings

            val existingConfig = Configuration.readConfig()
            existingConfig.deviceConfigurations[getVendorIdAsString()]!![getProductIdAsString()] = deviceConfiguration
            existingConfig.writeConfig()

            if (DriverSocket.connected) {
                DriverPackets.reloadConfiguration().writeToOutputStream(DriverSocket.getOutputStream()!!)
            }
        }
    }

    companion object {
        fun getProductId(): Short {
            return 2309
        }

        fun getVendorId(): Short {
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
            return "XP-Pen Deco 01v2"
        }
    }
}