package dev.villanueva.userland_utility.products.xppen

import com.fasterxml.jackson.databind.ObjectMapper
import dev.villanueva.userland_utility.iterop.DriverPacketHandler
import dev.villanueva.userland_utility.iterop.DriverSocket
import dev.villanueva.userland_utility.products.Configuration
import dev.villanueva.userland_utility.products.DeviceConfiguration
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.newsclub.net.unix.AFUNIXSocket
import org.newsclub.net.unix.AFUNIXSocketAddress
import tornadofx.Controller
import java.io.File
import java.net.SocketException

class DecoProController : Controller() {
    val mapItems: ObservableList<MappableItem> = FXCollections.observableArrayList()
    private val buttonBindings: HashMap<String, HashMap<String, HashSet<Int>>> = HashMap()
    private val dialBindings: HashMap<String, HashMap<String, HashMap<String, HashSet<Int>>>> = HashMap()

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

    fun updateMapping(itemType: MappableItemType, referenceId: Int, mappedValues: HashSet<Int>, matchValue: Int, mappedItemType: MappableItemType) {
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

    fun commitChanges(deviceConfiguration: DeviceConfiguration?) {
        println("Committing changes")
        if (deviceConfiguration != null) {
            deviceConfiguration.mapping.buttons = this.buttonBindings
            deviceConfiguration.mapping.dials = this.dialBindings

            val configFile = File("/home/aren/.local/share/xp_pen_userland/driver.cfg")
            val jsonInput = configFile.inputStream().readAllBytes()
            val mapper = ObjectMapper()

            val existingConfig = mapper.readValue(jsonInput, Configuration::class.java)
            existingConfig.deviceConfigurations["10429"]!!["2313"] = deviceConfiguration

            val config = mapper.writeValueAsString(existingConfig)
            configFile.outputStream().write(config.toByteArray())

            if (DriverSocket.connected) {
                // Send through a config reload
                val packet = DriverPacketHandler(
                    1,
                    0x0000,
                    0x0002,
                    0,
                    0,
                    true,
                    0,
                    0,
                    0,
                )

                packet.writeToOutputStream(DriverSocket.getOutputStream()!!)
            }
        }
    }
}