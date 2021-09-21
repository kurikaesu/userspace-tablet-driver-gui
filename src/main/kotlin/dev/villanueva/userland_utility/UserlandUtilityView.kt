package dev.villanueva.userland_utility

import dev.villanueva.userland_utility.products.DeviceConfiguration
import dev.villanueva.userland_utility.products.xppen.DecoProView
import javafx.scene.control.ListView
import javafx.scene.control.SelectionMode
import tornadofx.*

class UserlandUtilityView : View() {
    private val controller: UserlandUtilityController by inject()
    private var connectedDevices: MutableList<String> = mutableListOf()
    private var listView: ListView<String>? = null

    private var selectedConfig: DeviceConfiguration? = null

    override val root = form {
        listView = listview(connectedDevices.asObservable()) {
            selectionModel.selectionMode = SelectionMode.SINGLE
            onUserSelect(1) {
                selectedConfig = controller.getConfigForDevice(it)
            }
        }
        button("Refresh devices") {
            action {
                listView?.items?.clear()
                listView?.items?.addAll(controller.getConnectedDevices().toMutableList())
            }
        }
        button("Load Configuration") {
            action {
                find<DecoProView>(mapOf(DecoProView::deviceConfiguration to selectedConfig)).openWindow()
            }
        }
    }

    override fun onBeforeShow() {
        super.onBeforeShow()
        listView?.items?.addAll(controller.getConnectedDevices().toMutableList())
    }
}