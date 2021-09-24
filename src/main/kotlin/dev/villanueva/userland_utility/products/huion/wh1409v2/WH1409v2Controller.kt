package dev.villanueva.userland_utility.products.huion.wh1409v2

import dev.villanueva.userland_utility.iterop.DriverPackets
import dev.villanueva.userland_utility.iterop.DriverSocket
import dev.villanueva.userland_utility.products.DriverCodeIDs
import dev.villanueva.userland_utility.products.MappableItem
import dev.villanueva.userland_utility.products.MappableItemType
import dev.villanueva.userland_utility.products.ProductController
import dev.villanueva.userland_utility.products.config.Configuration
import dev.villanueva.userland_utility.products.config.DeviceConfiguration
import dev.villanueva.userland_utility.products.xppen.deco_pro.DecoProSmallController

class WH1409v2Controller : ProductController() {
    private val buttonBindings: HashMap<String, HashMap<String, HashSet<Int>>> = HashMap()
    private val dialBindings: HashMap<String, HashMap<String, HashMap<String, HashSet<Int>>>> = HashMap()

    init {
        mapItems.add(MappableItem(MappableItemType.Button, "K1", DriverCodeIDs.BTN_0.code, 0))
        mapItems.add(MappableItem(MappableItemType.Button, "K2", DriverCodeIDs.BTN_1.code, 0))
        mapItems.add(MappableItem(MappableItemType.Button, "K3", DriverCodeIDs.BTN_2.code, 0))
        mapItems.add(MappableItem(MappableItemType.Button, "K4", DriverCodeIDs.BTN_3.code, 0))
        mapItems.add(MappableItem(MappableItemType.Button, "K5", DriverCodeIDs.BTN_4.code, 0))
        mapItems.add(MappableItem(MappableItemType.Button, "K6", DriverCodeIDs.BTN_5.code, 0))
        mapItems.add(MappableItem(MappableItemType.Button, "K7", DriverCodeIDs.BTN_6.code, 0))
        mapItems.add(MappableItem(MappableItemType.Button, "K8", DriverCodeIDs.BTN_7.code, 0))
        mapItems.add(MappableItem(MappableItemType.Button, "K9", DriverCodeIDs.BTN_8.code, 0))
        mapItems.add(MappableItem(MappableItemType.Button, "K10", DriverCodeIDs.BTN_9.code, 0))
        mapItems.add(MappableItem(MappableItemType.Button, "K11", DriverCodeIDs.BTN_SOUTH.code, 0))
        mapItems.add(MappableItem(MappableItemType.Button, "K2", DriverCodeIDs.BTN_EAST.code, 0))
    }

    override fun updateMapping(
        itemType: MappableItemType,
        referenceId: Int,
        mappedValues: HashSet<Int>,
        matchValue: Int,
        mappedItemType: MappableItemType
    ) {
        val referenceIdString = referenceId.toString()
        val mappedItemTypeString = mappedItemType.value.toString()
        if (itemType == MappableItemType.Button) {
            if (!buttonBindings.containsKey(referenceIdString)) {
                buttonBindings[referenceIdString] = HashMap()
            }

            buttonBindings[referenceIdString]!![mappedItemTypeString] = HashSet(mappedValues)
        } else if (itemType == MappableItemType.Dial) {
            val matchValueString = matchValue.toString()
            if (!dialBindings.containsKey(referenceIdString)) {
                dialBindings[referenceIdString] = HashMap()
            }

            if (!dialBindings[referenceIdString]!!.containsKey(matchValueString)) {
                dialBindings[referenceIdString]!![matchValueString] = HashMap()
            }

            dialBindings[referenceIdString]!![matchValueString]!![mappedItemTypeString] = HashSet(mappedValues)
        }
    }

    override fun commitChanges(deviceConfiguration: DeviceConfiguration?) {
        println("Committing changes")
        if (deviceConfiguration != null) {
            deviceConfiguration.mapping.buttons = this.buttonBindings
            deviceConfiguration.mapping.dials = this.dialBindings

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
            return 392
        }

        fun getVendorId(): Short {
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
            return "Huion WH1409 v2"
        }
    }
}