package dev.villanueva.userland_utility

import dev.villanueva.userland_utility.iterop.DriverPacketHandler
import dev.villanueva.userland_utility.iterop.DriverPackets
import dev.villanueva.userland_utility.iterop.DriverSocket
import dev.villanueva.userland_utility.products.Configuration
import dev.villanueva.userland_utility.products.DeviceConfiguration
import tornadofx.Controller
import java.nio.ByteBuffer
import java.nio.ByteOrder

class UserlandUtilityController: Controller() {
    private val connectedDevices: HashSet<String> = HashSet()

    fun getConnectedDevices(): HashSet<String> {
        connectedDevices.clear()

        if (DriverSocket.connected) {
            DriverPackets.getConnectedDevices().writeToOutputStream(DriverSocket.getOutputStream()!!)
            val response = DriverPacketHandler.readFromInputStream(DriverSocket.getInputStream()!!)
            if (response != null) {
                val responseBuffer = ByteBuffer.wrap(response.data)
                responseBuffer.order(ByteOrder.LITTLE_ENDIAN)
                while (responseBuffer.hasRemaining()) {
                    val vendor = responseBuffer.short
                    val product = responseBuffer.short

                    val vendorString = String.format("%d", vendor)
                    val productString = String.format("%d", product)
                    println("Vendor $vendorString and product $productString")
                    connectedDevices.add("$vendorString:$productString")
                }
            } else {
                println("Did not get a response from the driver")
            }
        }

        return HashSet(connectedDevices)
    }

    fun getConfigForDevice(deviceName: String): DeviceConfiguration? {
        val parts = deviceName.split(":")
        val config = Configuration.readConfig()
        val vendorConfig = config.deviceConfigurations[parts[0]]
        if (vendorConfig != null) {
            val productConfig = vendorConfig[parts[1]]
            if (productConfig != null) {
                return productConfig
            }
        }

        return null
    }
}