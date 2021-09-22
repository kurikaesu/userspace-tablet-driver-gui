package dev.villanueva.userland_utility.iterop

import java.io.InputStream
import java.io.OutputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

class DriverPacketHandler(
    private val direction: Int,
    private val vendor: Short,
    private val device: Short,
    private val iface: Short,
    private val length: Long,
    private val expectResponse: Boolean,
    private val responseLength: Long,
    private val responseInterface: Short,
    private val originatingSocket: Int,
    private val signature: Long? = getCurrentSignature(),
    val data: ByteArray? = null,
) {
    fun writeToOutputStream(outputStream: OutputStream) {
        val buffer = ByteBuffer.allocate((56 + length).toInt())
        buffer.order(ByteOrder.LITTLE_ENDIAN)
        buffer.putInt(direction)
        buffer.putShort(vendor)
        buffer.putShort(device)
        buffer.putLong(iface.toLong())
        buffer.putLong(length)
        if (expectResponse)
            buffer.putLong(0x01)
        else
            buffer.putLong(0x00)
        buffer.putLong(responseLength)
        buffer.putInt(responseInterface.toInt())
        buffer.putInt(originatingSocket)
        buffer.putLong(signature!!)
        if (data != null) {
            buffer.put(data)
        }
        buffer.flip()

        outputStream.write(buffer.array())
        outputStream.flush()
    }

    companion object {
        fun readFromInputStream(inputStream: InputStream): DriverPacketHandler? {
            val buffer = ByteBuffer.allocate(4096)
            buffer.order(ByteOrder.LITTLE_ENDIAN)

            var readBytes = 0
            var totalRead = 0
            val headerBytes = ByteArray(56)
            while (readBytes != -1 && totalRead != 56) {
                readBytes = inputStream.read(headerBytes, totalRead, 56 - totalRead)
                if (readBytes > 0)
                    totalRead += readBytes
            }

            buffer.put(headerBytes)
            buffer.flip()

            val direction = buffer.int
            val vendor = buffer.short
            val device = buffer.short
            val iface = buffer.long.toShort()
            val length = buffer.long
            val expectResponse = (buffer.long == 1L)
            val responseLength = buffer.long
            val responseInterface = buffer.int.toShort()
            val originatingSocket = buffer.int
            val signature = buffer.long

            if (signature != getCurrentSignature()) {
                println("Signature is wrong!")
                return null
            }

            val data = ByteArray(length.toInt())
            totalRead = 0
            while ((readBytes != -1) && (totalRead != length.toInt())) {
                readBytes = inputStream.read(data, totalRead, length.toInt() - totalRead)
                if (readBytes > 0)
                    totalRead += readBytes
            }

            return DriverPacketHandler(direction, vendor, device, iface, length, expectResponse, responseLength, responseInterface, originatingSocket, signature, data)
        }

        fun getCurrentSignature(): Long {
            return 53784359345776669
        }
    }
}