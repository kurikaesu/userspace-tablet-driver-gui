package dev.villanueva.userland_utility

import dev.villanueva.userland_utility.products.ProductView
import dev.villanueva.userland_utility.products.config.DeviceConfiguration
import dev.villanueva.userland_utility.products.SupportedProducts
import javafx.scene.control.ListView
import javafx.scene.control.SelectionMode
import tornadofx.*

class UserlandUtilityView : View() {
    private val controller: UserlandUtilityController by inject()
    private var connectedDevices: MutableList<String> = mutableListOf()
    private var listView: ListView<String>? = null

    private var selectedConfig: DeviceConfiguration? = null

    override val root = form {
        title = "Connected devices"
        listView = listview(connectedDevices.asObservable()) {
            selectionModel.selectionMode = SelectionMode.SINGLE
            onUserSelect(1) {
                selectedConfig = controller.getConfigForDevice(it)
            }
        }
        button("Refresh devices") {
            useMaxWidth = true
            action {
                listView?.items?.clear()
                listView?.items?.addAll(controller.getConnectedDevices().toMutableList())
            }
        }
        button("Load Configuration") {
            useMaxWidth = true
            action {
                if (listView != null && listView?.selectedItem != null) {
                    val view = SupportedProducts.getViewForProduct(listView?.selectedItem!!)
                    if (view != null) {
                        find(
                            view,
                            mapOf(
                                SupportedProducts.getConfigPropertyForProduct(listView?.selectedItem!!) to selectedConfig,
                                ProductView::deviceName to listView?.selectedItem)
                        ).openWindow()
                    }
                }
            }
        }
    }

    override fun onBeforeShow() {
        super.onBeforeShow()
        listView?.items?.addAll(controller.getConnectedDevices().toMutableList())
    }
}