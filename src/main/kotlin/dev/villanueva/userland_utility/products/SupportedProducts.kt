package dev.villanueva.userland_utility.products

import dev.villanueva.userland_utility.products.config.DeviceConfiguration
import dev.villanueva.userland_utility.products.gaomon.m10kpro.M10KProController
import dev.villanueva.userland_utility.products.gaomon.m10kpro.M10KProView
import dev.villanueva.userland_utility.products.gaomon.m10kpro.M10K2018Controller
import dev.villanueva.userland_utility.products.gaomon.m10kpro.M10K2018View
import dev.villanueva.userland_utility.products.huion.h1161.H1161Controller
import dev.villanueva.userland_utility.products.huion.h1161.H1161View
import dev.villanueva.userland_utility.products.huion.kamvas_pro_13.KamvasPro13Controller
import dev.villanueva.userland_utility.products.huion.kamvas_pro_13.KamvasPro13View
import dev.villanueva.userland_utility.products.huion.kd100.KD100Controller
import dev.villanueva.userland_utility.products.huion.kd100.KD100View
import dev.villanueva.userland_utility.products.huion.wh1409_2048.WH1409_2048Controller
import dev.villanueva.userland_utility.products.huion.wh1409_2048.WH1409_2048View
import dev.villanueva.userland_utility.products.huion.wh1409v2.WH1409v2Controller
import dev.villanueva.userland_utility.products.huion.wh1409v2.WH1409v2View
import dev.villanueva.userland_utility.products.xppen.ac19.AC19Controller
import dev.villanueva.userland_utility.products.xppen.ac19.AC19View
import dev.villanueva.userland_utility.products.xppen.artist_12.Artist12Gen2Controller
import dev.villanueva.userland_utility.products.xppen.artist_12.Artist12Gen2View
import dev.villanueva.userland_utility.products.xppen.artist_12_pro.Artist12ProController
import dev.villanueva.userland_utility.products.xppen.artist_12_pro.Artist12ProGen2Controller
import dev.villanueva.userland_utility.products.xppen.artist_12_pro.Artist12ProGen2View
import dev.villanueva.userland_utility.products.xppen.artist_12_pro.Artist12ProView
import dev.villanueva.userland_utility.products.xppen.artist_13_3_pro.Artist13_3ProController
import dev.villanueva.userland_utility.products.xppen.artist_13_3_pro.Artist13_3ProView
import dev.villanueva.userland_utility.products.xppen.artist_15_6_pro.Artist15_6ProController
import dev.villanueva.userland_utility.products.xppen.artist_15_6_pro.Artist15_6ProView
import dev.villanueva.userland_utility.products.xppen.artist_16_pro.Artist16ProController
import dev.villanueva.userland_utility.products.xppen.artist_22r_pro.Artist22rProController
import dev.villanueva.userland_utility.products.xppen.artist_22r_pro.Artist22rProView
import dev.villanueva.userland_utility.products.xppen.artist_22e_pro.Artist22eProController
import dev.villanueva.userland_utility.products.xppen.artist_22e_pro.Artist22eProView
import dev.villanueva.userland_utility.products.xppen.artist_24_pro.Artist24ProController
import dev.villanueva.userland_utility.products.xppen.artist_24_pro.Artist24ProView
import dev.villanueva.userland_utility.products.xppen.artist_16_pro.Artist16ProView
import dev.villanueva.userland_utility.products.xppen.artist_pro_16.ArtistPro16Controller
import dev.villanueva.userland_utility.products.xppen.artist_pro_16.ArtistPro16View
import dev.villanueva.userland_utility.products.xppen.deco.*
import dev.villanueva.userland_utility.products.xppen.deco_mini.DecoMini7Controller
import dev.villanueva.userland_utility.products.xppen.deco_mini.DecoMini7View
import dev.villanueva.userland_utility.products.xppen.deco_pro.DecoProMediumController
import dev.villanueva.userland_utility.products.xppen.deco_pro.DecoProMediumView
import dev.villanueva.userland_utility.products.xppen.deco_pro.DecoProMediumWirelessController
import dev.villanueva.userland_utility.products.xppen.deco_pro.DecoProMediumWirelessView
import dev.villanueva.userland_utility.products.xppen.deco_pro.DecoProSmallController
import dev.villanueva.userland_utility.products.xppen.deco_pro.DecoProSmallView
import dev.villanueva.userland_utility.products.xppen.innovator_16.Innovator16Controller
import dev.villanueva.userland_utility.products.xppen.innovator_16.Innovator16View
import dev.villanueva.userland_utility.products.xppen.star.StarG430SController
import dev.villanueva.userland_utility.products.xppen.star.StarG430SView
import dev.villanueva.userland_utility.products.xppen.star.StarG640Controller
import dev.villanueva.userland_utility.products.xppen.star.StarG640View
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

object SupportedProducts {
    private val productToClassMap: MutableMap<String, KClass<out ProductView>> = HashMap()
    private val nameToProductIdMap: MutableMap<String, String> = HashMap()
    private val productIdToName: MutableMap<String, String> = HashMap()
    private val viewDeviceConfigurationMap: MutableMap<String, KProperty1<out ProductView, DeviceConfiguration?>> = HashMap()

    init {
        // XP-Pen Deco Pro Small
        productToClassMap[DecoProSmallController.getProductName()] = DecoProSmallView::class
        nameToProductIdMap[DecoProSmallController.getProductName()] = DecoProSmallController.getVendorProductString()
        productIdToName[DecoProSmallController.getVendorProductString()] = DecoProSmallController.getProductName()
        viewDeviceConfigurationMap[DecoProSmallController.getProductName()] = DecoProSmallView::deviceConfiguration

        // XP-Pen Deco Pro Medium
        productToClassMap[DecoProMediumController.getProductName()] = DecoProMediumView::class
        nameToProductIdMap[DecoProMediumController.getProductName()] = DecoProMediumController.getVendorProductString()
        productIdToName[DecoProMediumController.getVendorProductString()] = DecoProMediumController.getProductName()
        viewDeviceConfigurationMap[DecoProMediumController.getProductName()] = DecoProMediumView::deviceConfiguration

        // XP-Pen Deco Pro Medium Wireless
        productToClassMap[DecoProMediumWirelessController.getProductName()] = DecoProMediumWirelessView::class
        nameToProductIdMap[DecoProMediumWirelessController.getProductName()] = DecoProMediumWirelessController.getVendorProductString()
        productIdToName[DecoProMediumWirelessController.getVendorProductString()] = DecoProMediumWirelessController.getProductName()
        viewDeviceConfigurationMap[DecoProMediumWirelessController.getProductName()] = DecoProMediumWirelessView::deviceConfiguration

        // XP-Pen Deco 01v2
        productToClassMap[Deco01v2Controller.getProductName()] = Deco01v2View::class
        nameToProductIdMap[Deco01v2Controller.getProductName()] = Deco01v2Controller.getVendorProductString()
        productIdToName[Deco01v2Controller.getVendorProductString()] = Deco01v2Controller.getProductName()
        viewDeviceConfigurationMap[Deco01v2Controller.getProductName()] = Deco01v2View::deviceConfiguration

        // XP-Pen Deco 02
        productToClassMap[Deco02Controller.getProductName()] = Deco02View::class
        nameToProductIdMap[Deco02Controller.getProductName()] = Deco02Controller.getVendorProductString()
        productIdToName[Deco02Controller.getVendorProductString()] = Deco02Controller.getProductName()
        viewDeviceConfigurationMap[Deco02Controller.getProductName()] = Deco02View::deviceConfiguration

        // XP-Pen Deco 03
        productToClassMap[Deco03Controller.getProductName()] = Deco03View::class
        nameToProductIdMap[Deco03Controller.getProductName()] = Deco03Controller.getVendorProductString()
        productIdToName[Deco03Controller.getVendorProductString()] = Deco03Controller.getProductName()
        viewDeviceConfigurationMap[Deco03Controller.getProductName()] = Deco03View::deviceConfiguration

        // XP-Pen Deco mini7
        productToClassMap[DecoMini7Controller.getProductName()] = DecoMini7View::class
        nameToProductIdMap[DecoMini7Controller.getProductName()] = DecoMini7Controller.getVendorProductString()
        productIdToName[DecoMini7Controller.getVendorProductString()] = DecoMini7Controller.getProductName()
        viewDeviceConfigurationMap[DecoMini7Controller.getProductName()] = DecoMini7View::deviceConfiguration

        // XP-Pen Artist 12 Pro
        productToClassMap[Artist12ProController.getProductName()] = Artist12ProView::class
        nameToProductIdMap[Artist12ProController.getProductName()] = Artist12ProController.getVendorProductString()
        productIdToName[Artist12ProController.getVendorProductString()] = Artist12ProController.getProductName()
        viewDeviceConfigurationMap[Artist12ProController.getProductName()] = Artist12ProView::deviceConfiguration

        // XP-Pen Artist 12 Pro (2nd Gen)
        productToClassMap[Artist12ProGen2Controller.getProductName()] = Artist12ProGen2View::class
        nameToProductIdMap[Artist12ProGen2Controller.getProductName()] = Artist12ProGen2Controller.getVendorProductString()
        productIdToName[Artist12ProGen2Controller.getVendorProductString()] = Artist12ProGen2Controller.getProductName()
        viewDeviceConfigurationMap[Artist12ProGen2Controller.getProductName()] = Artist12ProGen2View::deviceConfiguration

        // XP-Pen Artist 12 (2nd Gen)
        productToClassMap[Artist12Gen2Controller.getProductName()] = Artist12Gen2View::class
        nameToProductIdMap[Artist12Gen2Controller.getProductName()] = Artist12Gen2Controller.getVendorProductString()
        productIdToName[Artist12Gen2Controller.getVendorProductString()] = Artist12Gen2Controller.getProductName()
        viewDeviceConfigurationMap[Artist12Gen2Controller.getProductName()] = Artist12Gen2View::deviceConfiguration

        // XP-Pen Artist 13.3 Pro
        productToClassMap[Artist13_3ProController.getProductName()] = Artist13_3ProView::class
        nameToProductIdMap[Artist13_3ProController.getProductName()] = Artist13_3ProController.getVendorProductString()
        productIdToName[Artist13_3ProController.getVendorProductString()] = Artist13_3ProController.getProductName()
        viewDeviceConfigurationMap[Artist13_3ProController.getProductName()] = Artist13_3ProView::deviceConfiguration

        // XP-Pen Artist 15.6 Pro
        productToClassMap[Artist15_6ProController.getProductName()] = Artist15_6ProView::class
        nameToProductIdMap[Artist15_6ProController.getProductName()] = Artist15_6ProController.getVendorProductString()
        productIdToName[Artist15_6ProController.getVendorProductString()] = Artist15_6ProController.getProductName()
        viewDeviceConfigurationMap[Artist15_6ProController.getProductName()] = Artist15_6ProView::deviceConfiguration

        // XP-Pen Artist 16 Pro
        productToClassMap[Artist16ProController.getProductName()] = Artist16ProView::class
        nameToProductIdMap[Artist16ProController.getProductName()] = Artist16ProController.getVendorProductString()
        productIdToName[Artist16ProController.getVendorProductString()] = Artist16ProController.getProductName()
        viewDeviceConfigurationMap[Artist16ProController.getProductName()] = Artist16ProView::deviceConfiguration

        // XP-Pen Artist Pro 16
        productToClassMap[ArtistPro16Controller.getProductName()] = ArtistPro16View::class
        nameToProductIdMap[ArtistPro16Controller.getProductName()] = ArtistPro16Controller.getVendorProductString()
        productIdToName[ArtistPro16Controller.getVendorProductString()] = ArtistPro16Controller.getProductName()
        viewDeviceConfigurationMap[ArtistPro16Controller.getProductName()] = ArtistPro16View::deviceConfiguration

        // XP-Pen Artist 22r Pro
        productToClassMap[Artist22rProController.getProductName()] = Artist22rProView::class
        nameToProductIdMap[Artist22rProController.getProductName()] = Artist22rProController.getVendorProductString()
        productIdToName[Artist22rProController.getVendorProductString()] = Artist22rProController.getProductName()
        viewDeviceConfigurationMap[Artist22rProController.getProductName()] = Artist22rProView::deviceConfiguration

        // XP-Pen Artist 22E Pro
        productToClassMap[Artist22eProController.getProductName()] = Artist22eProView::class
        nameToProductIdMap[Artist22eProController.getProductName()] = Artist22eProController.getVendorProductString()
        productIdToName[Artist22eProController.getVendorProductString()] = Artist22eProController.getProductName()
        viewDeviceConfigurationMap[Artist22eProController.getProductName()] = Artist22eProView::deviceConfiguration

        // XP-Pen Artist 24 Pro
        productToClassMap[Artist24ProController.getProductName()] = Artist24ProView::class
        nameToProductIdMap[Artist24ProController.getProductName()] = Artist24ProController.getVendorProductString()
        productIdToName[Artist24ProController.getVendorProductString()] = Artist24ProController.getProductName()
        viewDeviceConfigurationMap[Artist24ProController.getProductName()] = Artist24ProView::deviceConfiguration

        // XP-Pen Innovator 16
        productToClassMap[Innovator16Controller.getProductName()] = Innovator16View::class
        nameToProductIdMap[Innovator16Controller.getProductName()] = Innovator16Controller.getVendorProductString()
        productIdToName[Innovator16Controller.getVendorProductString()] = Innovator16Controller.getProductName()
        viewDeviceConfigurationMap[Innovator16Controller.getProductName()] = Innovator16View::deviceConfiguration

        // XP-Pen Star G430S
        productToClassMap[StarG430SController.getProductName()] = StarG430SView::class
        nameToProductIdMap[StarG430SController.getProductName()] = StarG430SController.getVendorProductString()
        productIdToName[StarG430SController.getVendorProductString()] = StarG430SController.getProductName()
        viewDeviceConfigurationMap[StarG430SController.getProductName()] = StarG430SView::deviceConfiguration

        // XP-Pen Star G640
        productToClassMap[StarG640Controller.getProductName()] = StarG640View::class
        nameToProductIdMap[StarG640Controller.getProductName()] = StarG640Controller.getVendorProductString()
        productIdToName[StarG640Controller.getVendorProductString()] = StarG640Controller.getProductName()
        viewDeviceConfigurationMap[StarG640Controller.getProductName()] = StarG640View::deviceConfiguration

        // XP-Pen AC19 Shortcut Remote
        productToClassMap[AC19Controller.getProductName()] = AC19View::class
        nameToProductIdMap[AC19Controller.getProductName()] = AC19Controller.getVendorProductString()
        productIdToName[AC19Controller.getVendorProductString()] = AC19Controller.getProductName()
        viewDeviceConfigurationMap[AC19Controller.getProductName()] = AC19View::deviceConfiguration

        // Huion Kamvas Pro 13
        productToClassMap[KamvasPro13Controller.getProductName()] = KamvasPro13View::class
        nameToProductIdMap[KamvasPro13Controller.getProductName()] = KamvasPro13Controller.getVendorProductString()
        productIdToName[KamvasPro13Controller.getVendorProductString()] = KamvasPro13Controller.getProductName()
        viewDeviceConfigurationMap[KamvasPro13Controller.getProductName()] = KamvasPro13View::deviceConfiguration

        // Huion WH1409 v2
        productToClassMap[WH1409v2Controller.getProductName()] = WH1409v2View::class
        nameToProductIdMap[WH1409v2Controller.getProductName()] = WH1409v2Controller.getVendorProductString()
        productIdToName[WH1409v2Controller.getVendorProductString()] = WH1409v2Controller.getProductName()
        viewDeviceConfigurationMap[WH1409v2Controller.getProductName()] = WH1409v2View::deviceConfiguration

        // Huion WH1409 (2048)
        productToClassMap[WH1409_2048Controller.getProductName()] = WH1409_2048View::class
        nameToProductIdMap[WH1409_2048Controller.getProductName()] = WH1409_2048Controller.getVendorProductString()
        productIdToName[WH1409_2048Controller.getVendorProductString()] = WH1409_2048Controller.getProductName()
        viewDeviceConfigurationMap[WH1409_2048Controller.getProductName()] = WH1409_2048View::deviceConfiguration

        // Huion H1161
        productToClassMap[H1161Controller.getProductName()] = H1161View::class
        nameToProductIdMap[H1161Controller.getProductName()] = H1161Controller.getVendorProductString()
        productIdToName[H1161Controller.getVendorProductString()] = H1161Controller.getProductName()
        viewDeviceConfigurationMap[H1161Controller.getProductName()] = H1161View::deviceConfiguration

        // Huion KD100
        productToClassMap[KD100Controller.getProductName()] = KD100View::class
        nameToProductIdMap[KD100Controller.getProductName()] = KD100Controller.getVendorProductString()
        productIdToName[KD100Controller.getVendorProductString()] = KD100Controller.getProductName()
        viewDeviceConfigurationMap[KD100Controller.getProductName()] = KD100View::deviceConfiguration

        // Gaomon M10K Pro
        productToClassMap[M10KProController.getProductName()] = M10KProView::class
        nameToProductIdMap[M10KProController.getProductName()] = M10KProController.getVendorProductString()
        productIdToName[M10KProController.getVendorProductString()] = M10KProController.getProductName()
        viewDeviceConfigurationMap[M10KProController.getProductName()] = M10KProView::deviceConfiguration

        // Gaomon M10K 2018
        productToClassMap[M10K2018Controller.getProductName()] = M10K2018View::class
        nameToProductIdMap[M10K2018Controller.getProductName()] = M10K2018Controller.getVendorProductString()
        productIdToName[M10K2018Controller.getVendorProductString()] = M10K2018Controller.getProductName()
        viewDeviceConfigurationMap[M10K2018Controller.getProductName()] = M10K2018View::deviceConfiguration
    }

    fun getViewForProduct(product: String): KClass<out ProductView>? {
        return productToClassMap[product]
    }

    fun getProductIdFromName(name: String): String {
        return nameToProductIdMap[name] ?: "0000:0000"
    }

    fun getNameOfProduct(vendorId: Short, productId: Short): String {
        return productIdToName["$vendorId:$productId"] ?: "Unknown device $vendorId:$productId"
    }

    fun getConfigPropertyForProduct(deviceName: String): KProperty1<out ProductView, DeviceConfiguration?> {
        return viewDeviceConfigurationMap[deviceName]!!
    }
}
