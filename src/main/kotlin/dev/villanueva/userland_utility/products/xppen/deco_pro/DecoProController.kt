package dev.villanueva.userland_utility.products.xppen.deco_pro

import dev.villanueva.userland_utility.iterop.DriverPacketHandler
import dev.villanueva.userland_utility.iterop.DriverPackets
import dev.villanueva.userland_utility.iterop.DriverSocket
import dev.villanueva.userland_utility.products.DriverCodeIDs
import dev.villanueva.userland_utility.products.MappableItem
import dev.villanueva.userland_utility.products.MappableItemType
import dev.villanueva.userland_utility.products.ProductController

open class DecoProController : ProductController() {
    init {
        mapItems.add(MappableItem(MappableItemType.Button, "K1", DriverCodeIDs.BTN_0.code, 0))
        mapItems.add(MappableItem(MappableItemType.Button, "K2", DriverCodeIDs.BTN_1.code, 0))
        mapItems.add(MappableItem(MappableItemType.Button, "K3", DriverCodeIDs.BTN_2.code, 0))
        mapItems.add(MappableItem(MappableItemType.Button, "K4", DriverCodeIDs.BTN_3.code, 0))
        mapItems.add(MappableItem(MappableItemType.Dial, "Outer Dial -> Left", DriverCodeIDs.REL_WHEEL.code, -1))
        mapItems.add(MappableItem(MappableItemType.Dial, "Outer Dial -> Right", DriverCodeIDs.REL_WHEEL.code, 1))
        mapItems.add(MappableItem(MappableItemType.Dial, "Inner Dial -> Left", DriverCodeIDs.REL_HWHEEL.code, -1))
        mapItems.add(MappableItem(MappableItemType.Dial, "Inner Dial -> Right", DriverCodeIDs.REL_HWHEEL.code, 1))
        mapItems.add(MappableItem(MappableItemType.Button, "K5", DriverCodeIDs.BTN_4.code, 0))
        mapItems.add(MappableItem(MappableItemType.Button, "K6", DriverCodeIDs.BTN_5.code, 0))
        mapItems.add(MappableItem(MappableItemType.Button, "K7", DriverCodeIDs.BTN_6.code, 0))
        mapItems.add(MappableItem(MappableItemType.Button, "K8", DriverCodeIDs.BTN_7.code, 0))
    }

    protected fun setTouchPadToSettingForProduct(productId: Short, setting: Int) {
        val driverPacketHandler: DriverPacketHandler = when (setting) {
            1 -> DriverPackets.decoProSetTouchpadMode(productId, 1)
            2 -> DriverPackets.decoProSetTouchpadMode(productId, 2)
            3 -> DriverPackets.decoProSetTouchpadMode(productId, 3)
            else -> {
                println("Unknown setting $setting")
                return
            }
        }

        if (DriverSocket.connected) {
            driverPacketHandler.writeToOutputStream(DriverSocket.getOutputStream()!!)
        }
    }
}