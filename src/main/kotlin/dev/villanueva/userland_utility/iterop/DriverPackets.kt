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
}