package dev.villanueva.userland_utility.products.xppen.deco_pro

import dev.villanueva.userland_utility.products.config.Configuration
import dev.villanueva.userland_utility.products.config.DeviceConfiguration

class DecoProSmallController : DecoProController() {
    fun setTouchPadToSetting(setting: Int) {
        setTouchPadToSettingForProduct(getProductId(), setting)
    }

    override fun updateExistingDeviceConfig(deviceConfiguration: DeviceConfiguration): Configuration {
        val existingConfig = Configuration.readConfig()
        existingConfig.deviceConfigurations[getVendorIdAsString()]!![getProductIdAsString()] = deviceConfiguration
        return existingConfig
    }

    companion object {
        private fun getProductId(): Short {
            return 2313
        }

        private fun getVendorId(): Short {
            return 10429
        }

        fun getProductIdAsString(): String {
            return getProductId().toString()
        }

        fun getVendorIdAsString(): String {
            return getVendorId().toString()
        }

        fun getVendorProductString(): String {
            return "${getVendorId()}:${getProductId()}"
        }

        fun getProductName(): String {
            return "XP-Pen Deco Pro Small"
        }
    }
}