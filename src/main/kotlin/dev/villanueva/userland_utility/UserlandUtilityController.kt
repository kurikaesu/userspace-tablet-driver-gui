package dev.villanueva.userland_utility

import com.fasterxml.jackson.databind.ObjectMapper
import dev.villanueva.userland_utility.iterop.DriverPacketHandler
import dev.villanueva.userland_utility.products.Configuration
import dev.villanueva.userland_utility.products.DeviceConfiguration
import dev.villanueva.userland_utility.products.MappingConfiguration
import org.newsclub.net.unix.AFUNIXSocket
import org.newsclub.net.unix.AFUNIXSocketAddress
import tornadofx.Controller
import java.io.File
import java.net.SocketException
import java.nio.ByteBuffer
import java.nio.ByteOrder

class UserlandUtilityController: Controller() {
    private val sock: AFUNIXSocket = AFUNIXSocket.newInstance()
    private val connectedDevices: HashSet<String> = HashSet()
    private var connected: Boolean

    init {
        val sockPath = "${System.getenv("HOME")}/.local/var/run/xp_pen_userland.sock"
        val sockFile = File(sockPath)

        try {
            sock.connect(AFUNIXSocketAddress.of(sockFile))
        } catch (e: SocketException) {
            println("Is the driver not running? Could not connect to it")
            connected = false
        }

        connected = true
    }

    fun getConnectedDevices(): HashSet<String> {
        connectedDevices.clear()

        if (connected) {
            val packet = DriverPacketHandler(
                1,
                0x0000,
                0x0001,
                0,
                0,
                true,
                0,
                0,
                0,
            )

            packet.writeToOutputStream(sock.outputStream)

            val response = DriverPacketHandler.readFromInputStream(sock.inputStream)
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
        val configFile = File("/home/aren/.local/share/xp_pen_userland/driver.cfg")
        val jsonInput = configFile.inputStream().readAllBytes()
        val mapper = ObjectMapper()
        val config = mapper.readValue(jsonInput, Configuration::class.java)
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