package dev.villanueva.userland_utility.iterop

object DriverPackets {
    fun getConnectedDevices(): DriverPacketHandler {
        return DriverPacketHandler(
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
    }

    fun reloadConfiguration(): DriverPacketHandler {
        return DriverPacketHandler(
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
    }

    fun decoProSetTouchpadMode(device: Short, mode: Byte): DriverPacketHandler {
        return DriverPacketHandler(
            0,
            0x28bd,
            device,
            0x03,
            10,
            true,
            10,
            0x03,
            0,
            data = byteArrayOf(0x02, 0xB4.toByte(), 0x02, 0x01, 0x00, mode, 0x00, 0x00, 0x00, 0x00)
        )
    }
}