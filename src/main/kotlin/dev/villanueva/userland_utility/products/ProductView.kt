package dev.villanueva.userland_utility.products

import com.github.kwhat.jnativehook.GlobalScreen
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent
import com.github.kwhat.jnativehook.mouse.NativeMouseListener
import com.github.kwhat.jnativehook.mouse.NativeMouseWheelEvent
import com.github.kwhat.jnativehook.mouse.NativeMouseWheelListener
import dev.villanueva.userland_utility.products.config.DeviceConfiguration
import dev.villanueva.userland_utility.products.converters.LinuxInputToFriendlyEvent
import javafx.application.Platform
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.input.KeyEvent
import tornadofx.View
import tornadofx.action
import tornadofx.fieldset
import tornadofx.field
import tornadofx.button


open class ProductView: View(), NativeKeyListener, NativeMouseListener, NativeMouseWheelListener {
    override val root: Parent
        get() = throw NotImplementedError("This class should not be called directly")

    open val deviceName: String by param()
    open val deviceConfiguration: DeviceConfiguration? by param()

    private var numKeysPressed: Int = 0
    private var keysPressed: LinkedHashSet<Int> = LinkedHashSet()
    private var keysReleased: LinkedHashSet<Int> = LinkedHashSet()
    private var mousePressed: LinkedHashSet<Int> = LinkedHashSet()
    private var mouseReleased: LinkedHashSet<Int> = LinkedHashSet()
    private var mouseWheelRotation: Int = 0

    var keyReleasedEventFunction: (it: NativeKeyEvent) -> Unit = {}
    var mouseReleasedEventFunction: (it: NativeMouseEvent) -> Unit = {}
    private val preventKeyPressFilter: (it: KeyEvent) -> Unit = {
        it.consume()
    }

    protected fun onKeyPressedFun(thisButton: Button, controller: ProductController, itemType: MappableItemType, referenceId: Int, matchValue: Int) {
        if (numKeysPressed != 0) {
            return
        }

        thisButton.text = "Press key combination"

        keyReleasedEventFunction = { it: NativeKeyEvent ->
            handleKeyReleased(it, controller, thisButton, itemType, referenceId, matchValue)
        }

        mouseReleasedEventFunction = { it: NativeMouseEvent ->
            handleMouseReleased(it, controller, thisButton, itemType, referenceId, matchValue)
        }

        GlobalScreen.addNativeKeyListener(this)
        GlobalScreen.addNativeMouseListener(this)
        GlobalScreen.addNativeMouseWheelListener(this)

        thisButton.addEventFilter(KeyEvent.KEY_PRESSED, preventKeyPressFilter)
    }

    override fun nativeKeyPressed(keyEvent: NativeKeyEvent) {
        val shiftedKey = LinuxInputToFriendlyEvent.translateShiftedKeys(keyEvent.rawCode, keyEvent.keyCode, keyEvent.keyLocation)

        if (shiftedKey == null) {
            println("Unknown shifted/raw key ${keyEvent.keyCode}/${keyEvent.rawCode}")
            return
        }

        if (!keysPressed.contains(shiftedKey)) {
            keysPressed.add(shiftedKey)
            numKeysPressed += 1

            println("Raw: ${keyEvent.rawCode} Key: ${keyEvent.keyCode} Location: ${keyEvent.keyLocation}")
        }
    }

    override fun nativeKeyReleased(keyEvent: NativeKeyEvent) {
        keyReleasedEventFunction(keyEvent)
    }

    private fun handleKeyReleased(keyEvent: NativeKeyEvent, controller: ProductController, thisButton: Button, itemType: MappableItemType, referenceId: Int, matchValue: Int ) {
        if (!keysReleased.contains(keyEvent.rawCode)) {
            keysReleased.add(keyEvent.rawCode)
            numKeysPressed -= 1

            handleAllKeysUp(controller, thisButton, itemType, referenceId, matchValue)
        }
    }

    override fun nativeMousePressed(nativeEvent: NativeMouseEvent) {
        var translatedButton = LinuxInputToFriendlyEvent.translateMouseEvent(nativeEvent.button)
        if (translatedButton == null) {
            println("No translation for mouse button ${nativeEvent.button}")
            translatedButton = nativeEvent.button
        }
        if (!mousePressed.contains(translatedButton)) {
            mousePressed.add(translatedButton)
            println(translatedButton)
            numKeysPressed+=1
        }
    }

    override fun nativeMouseReleased(nativeEvent: NativeMouseEvent) {
        mouseReleasedEventFunction(nativeEvent)
    }

    private fun handleMouseReleased(mouseEvent: NativeMouseEvent, controller: ProductController, thisButton: Button, itemType: MappableItemType, referenceId: Int, matchValue: Int) {
        if (!mouseReleased.contains(mouseEvent.button)) {
            mouseReleased.add(mouseEvent.button)
            numKeysPressed-=1

            handleAllKeysUp(controller, thisButton, itemType, referenceId, matchValue)
        }
    }

    private fun handleAllKeysUp(controller: ProductController, thisButton: Button, itemType: MappableItemType, referenceId: Int, matchValue: Int) {
        if (numKeysPressed <= 0) {
            thisButton.removeEventFilter(KeyEvent.KEY_PRESSED, preventKeyPressFilter)
            GlobalScreen.removeNativeKeyListener(this)
            GlobalScreen.removeNativeMouseListener(this)
            GlobalScreen.removeNativeMouseWheelListener(this)

            Platform.runLater {
                run {
                    val keyPressedString = keysPressed.joinToString(separator = "+") {
                        LinuxInputToFriendlyEvent.getKeyDisplayName(it) ?: "UNKNOWN"
                    }
                    val mousePressedString = mousePressed.joinToString(separator = "+") {
                        LinuxInputToFriendlyEvent.getMouseDisplayName(it) ?: "UNKNOWN"
                    }

                    thisButton.text = listOf(keyPressedString, mousePressedString).filter { it.isNotEmpty() }.joinToString(separator = "+")
                    controller.updateMapping(itemType, referenceId, keysPressed, matchValue, MappableItemType.Button)
                    controller.updateMapping(itemType, referenceId, mousePressed, matchValue, MappableItemType.Mouse)
                    keysPressed.clear()
                    keysReleased.clear()
                    mousePressed.clear()
                    mouseReleased.clear()
                }
            }
        }
    }

    override fun nativeMouseWheelMoved(nativeEvent: NativeMouseWheelEvent) {
        mouseWheelRotation = nativeEvent.wheelRotation
        // Not exactly sure what to do with wheels yet
    }

    private fun createButtonFromMappableItem(parent: Parent, item: MappableItem, controller: ProductController): Unit {
        with (parent) {
            button {
                var labelText = ""
                if (deviceConfiguration != null) {
                    if (item.itemType == MappableItemType.Button) {
                        val buttonMap = deviceConfiguration!!.mapping.buttons[item.driverCode.toString()]

                        if (buttonMap != null) {
                            val buttonValueString = MappableItemType.Button.value.toString()
                            if (buttonMap.containsKey((buttonValueString))) {
                                labelText =
                                    buttonMap[buttonValueString]!!.joinToString(separator = "+") {
                                        LinuxInputToFriendlyEvent.getKeyDisplayName(it) ?: "UNKNOWN"
                                    }
                                controller.updateMapping(
                                    item.itemType,
                                    item.driverCode,
                                    LinkedHashSet(buttonMap[buttonValueString]),
                                    0,
                                    MappableItemType.Button
                                )
                            }
                        }
                    } else if (item.itemType == MappableItemType.Dial) {
                        val dialMap =
                            deviceConfiguration!!.mapping.dials[item.driverCode.toString()]
                        if (dialMap != null) {
                            val itemMatchValueString = item.matchValue.toString()
                            if (dialMap.containsKey(itemMatchValueString)) {
                                val buttonValueString = MappableItemType.Button.value.toString()
                                if (dialMap[itemMatchValueString]!!.containsKey(buttonValueString)) {
                                    labelText =
                                        dialMap[itemMatchValueString]!![buttonValueString]!!.joinToString(
                                            separator = "+"
                                        ) {
                                            LinuxInputToFriendlyEvent.getKeyDisplayName(it)
                                                ?: "UNKNOWN"
                                        }
                                    controller.updateMapping(
                                        item.itemType,
                                        item.driverCode,
                                        LinkedHashSet(dialMap[itemMatchValueString]!![buttonValueString]),
                                        item.matchValue,
                                        MappableItemType.Button
                                    )
                                }
                            }
                        }
                    }

                    text = labelText

                    action {
                        onKeyPressedFun(
                            this,
                            controller,
                            item.itemType,
                            item.driverCode,
                            item.matchValue
                        )
                    }
                }
            }
        }
    }

    fun createFieldSetFromIterator(parent: Parent, itemIterator: MutableListIterator<MappableItem>, controller: ProductController): Unit {
        with (parent) {
            fieldset {
                while (itemIterator.hasNext()) {
                    val item = itemIterator.next()
                    field(item.itemName) {
                        if (item.itemType == MappableItemType.Button || item.itemType == MappableItemType.Dial) {
                            createButtonFromMappableItem(this, item, controller)
                        }
                    }
                }
            }
        }
    }
}
