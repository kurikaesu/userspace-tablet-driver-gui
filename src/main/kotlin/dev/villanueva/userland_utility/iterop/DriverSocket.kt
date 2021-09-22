package dev.villanueva.userland_utility.iterop

import org.newsclub.net.unix.AFUNIXSocket
import org.newsclub.net.unix.AFUNIXSocketAddress
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.net.SocketException

object DriverSocket {
    val connected: Boolean
    private val sock: AFUNIXSocket = AFUNIXSocket.newInstance()

    init {
        val sockPath = "${System.getenv("HOME")}/.local/var/run/xp_pen_userland.sock"
        val sockFile = File(sockPath)
        var success = false

        try {
            sock.connect(AFUNIXSocketAddress.of(sockFile))
            success = true
        } catch (e: SocketException) {
            println("Is the driver not running? Could not connect to it")
        }

        connected = success
    }

    fun getInputStream(): InputStream? {
        return if (connected) sock.inputStream else null
    }

    fun getOutputStream(): OutputStream? {
        return if (connected) sock.outputStream else null
    }
}