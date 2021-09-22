package dev.villanueva.userland_utility.products

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File

class Configuration {
    var deviceConfigurations: HashMap<String,
        HashMap<String,
            DeviceConfiguration>
            > = HashMap()

    fun writeConfig() {
        val configFile = File(configFilePath)
        val mapper = ObjectMapper()
        val config = mapper.writeValueAsString(this)
        configFile.outputStream().write(config.toByteArray())
    }

    companion object {
        private val configFilePath = "${System.getenv("HOME")}/.local/share/xp_pen_userland/driver.cfg"

        fun readConfig(): Configuration {
            val configFile = File(configFilePath)
            val mapper = ObjectMapper()
            val jsonInput = configFile.inputStream().readAllBytes()
            return mapper.readValue(jsonInput, Configuration::class.java)
        }
    }
}