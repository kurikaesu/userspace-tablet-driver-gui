package dev.villanueva.userland_utility.products.xppen.deco

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

class Deco01v2View : ProductView() {
    private val controller: Deco01v2Controller by inject()
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