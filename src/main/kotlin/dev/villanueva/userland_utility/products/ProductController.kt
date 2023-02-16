package dev.villanueva.userland_utility.products

import dev.villanueva.userland_utility.iterop.DriverPackets
import dev.villanueva.userland_utility.iterop.DriverSocket
import dev.villanueva.userland_utility.products.config.Configuration
import dev.villanueva.userland_utility.products.config.DeviceConfiguration
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.Controller

abstract class ProductController : Controller() {
    val mapItems: ObservableList<MappableItem> = FXCollections.observableArrayList()
    val mapLeftItems: ObservableList<MappableItem> = FXCollections.observableArrayList()
    val mapRightItems: ObservableList<MappableItem> = FXCollections.observableArrayList()
    private val stylusButtonMappings: HashMap<String, HashMap<String, LinkedHashSet<Int>>> = HashMap()
    private val buttonBindings: HashMap<String, HashMap<String, LinkedHashSet<Int>>> = HashMap()
    private val dialBindings: HashMap<String, HashMap<String, HashMap<String, LinkedHashSet<Int>>>> = HashMap()

    private val stylusButtonDisabled: HashSet<String> = HashSet()
    private val buttonDisabled: HashSet<String> = HashSet()
    private val dialDisabled: HashSet<String> = HashSet()

    open fun updateMapping(
        itemType: MappableItemType,
        referenceId: Int,
        mappedValues: LinkedHashSet<Int>,
        matchValue: Int,
        mappedItemType: MappableItemType) {

        val referenceIdString = referenceId.toString()
        val mappedItemTypeString = mappedItemType.value.toString()
        when (itemType) {
            MappableItemType.StylusButton -> {
                if (!stylusButtonMappings.containsKey(referenceIdString)) {
                    stylusButtonMappings[referenceIdString] = HashMap()
                }

                stylusButtonMappings[referenceIdString]!![mappedItemTypeString] = LinkedHashSet(mappedValues)
            }
            MappableItemType.Button -> {
                if (!buttonBindings.containsKey(referenceIdString)) {
                    buttonBindings[referenceIdString] = HashMap()
                }

                buttonBindings[referenceIdString]!![mappedItemTypeString] = LinkedHashSet(mappedValues)
            }
            MappableItemType.Dial -> {
                val matchValueString = matchValue.toString()
                if (!dialBindings.containsKey(referenceIdString)) {
                    dialBindings[referenceIdString] = HashMap()
                }

                if (!dialBindings[referenceIdString]!!.containsKey(matchValueString)) {
                    dialBindings[referenceIdString]!![matchValueString] = HashMap()
                }

                dialBindings[referenceIdString]!![matchValueString]!![mappedItemTypeString] = LinkedHashSet(mappedValues)
            }
            else -> {}
        }
    }

    open fun disableMapping(
        itemType: MappableItemType,
        referenceId: Int,
        disable: Boolean
    ) {
        val referenceIdString = referenceId.toString()
        when (itemType) {
            MappableItemType.StylusButton -> {
                if (disable) {
                    stylusButtonDisabled.add(referenceIdString)
                } else {
                    stylusButtonDisabled.remove(referenceIdString)
                }
            }
            MappableItemType.Button -> {
                if (disable) {
                    buttonDisabled.add(referenceIdString)
                } else {
                    buttonDisabled.remove(referenceIdString)
                }
            }
            MappableItemType.Dial -> {
                if (disable) {
                    dialDisabled.add(referenceIdString)
                } else {
                    dialDisabled.remove(referenceIdString)
                }
            }
            else -> {}
        }
    }

    open fun updateExistingDeviceConfig(deviceConfiguration: DeviceConfiguration): Configuration {
        return Configuration()
    }

    open fun commitChanges(deviceConfiguration: DeviceConfiguration?) {
        println("Committing changes")
        if (deviceConfiguration != null) {
            deviceConfiguration.mapping.stylusButtons = this.stylusButtonMappings
            deviceConfiguration.mapping.buttons = this.buttonBindings
            deviceConfiguration.mapping.dials = this.dialBindings

            deviceConfiguration.disabled.stylusButtons = this.stylusButtonDisabled
            deviceConfiguration.disabled.buttons = this.buttonDisabled
            deviceConfiguration.disabled.dials = this.dialDisabled

            val existingConfig = updateExistingDeviceConfig(deviceConfiguration)
            existingConfig.writeConfig()

            if (DriverSocket.connected) {
                DriverPackets.reloadConfiguration().writeToOutputStream(DriverSocket.getOutputStream()!!)
            }
        }
    }
}