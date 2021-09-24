package dev.villanueva.userland_utility.products.huion.wh1409v2

import dev.villanueva.userland_utility.products.MappableItemType
import dev.villanueva.userland_utility.products.ProductView
import dev.villanueva.userland_utility.products.config.DeviceConfiguration
import dev.villanueva.userland_utility.products.converters.LinuxInputToFriendlyEvent
import tornadofx.action
import tornadofx.button
import tornadofx.field
import tornadofx.fieldset
import tornadofx.form

import tornadofx.vbox

class WH1409v2View : ProductView() {
    private val controller: WH1409v2Controller by inject()
    val deviceConfiguration: DeviceConfiguration? by param()

    override val root = vbox {
        form {
            val itemIterator = controller.mapItems.listIterator()
            fieldset {
                while (itemIterator.hasNext()) {
                    val item = itemIterator.next()
                    field(item.itemName) {
                        if (item.itemType == MappableItemType.Button || item.itemType == MappableItemType.Dial) {
                            button {
                                var labelText = ""
                                if (deviceConfiguration != null) {
                                    if (item.itemType == MappableItemType.Button) {
                                        val buttonMap = deviceConfiguration!!.mapping.buttons[item.driverCode.toString()]
                                        val buttonValueString = MappableItemType.Button.value.toString()
                                        if (buttonMap!!.containsKey(buttonValueString)) {
                                            labelText = buttonMap[buttonValueString]!!.joinToString(separator = "+") {
                                                LinuxInputToFriendlyEvent.getKeyDisplayName(it) ?: "UNKNOWN"
                                            }
                                            controller.updateMapping(item.itemType, item.driverCode, HashSet(buttonMap[buttonValueString]), 0, MappableItemType.Button)
                                        }
                                    } else if (item.itemType == MappableItemType.Dial) {
                                        val dialMap = deviceConfiguration!!.mapping.dials[item.driverCode.toString()]
                                        val itemMatchValueString = item.matchValue.toString()
                                        if (dialMap!!.containsKey(itemMatchValueString)) {
                                            val buttonValueString = MappableItemType.Button.value.toString()
                                            if (dialMap[itemMatchValueString]!!.containsKey(buttonValueString)) {
                                                labelText = dialMap[itemMatchValueString]!![buttonValueString]!!.joinToString(separator = "+") {
                                                    LinuxInputToFriendlyEvent.getKeyDisplayName(it) ?: "UNKNOWN"
                                                }
                                                controller.updateMapping(item.itemType, item.driverCode, HashSet(dialMap[itemMatchValueString]!![buttonValueString]), item.matchValue, MappableItemType.Button)
                                            }
                                        }
                                    }
                                }

                                text = labelText

                                action { onKeyPressedFun(this, controller, item.itemType, item.driverCode, item.matchValue) }
                            }
                        }
                    }
                }
            }
            button("Save/Submit Configuration") {
                action {
                    controller.commitChanges(deviceConfiguration)
                }
            }
        }
    }
}