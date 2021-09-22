package dev.villanueva.userland_utility

import com.github.kwhat.jnativehook.GlobalScreen
import com.github.kwhat.jnativehook.NativeHookException
import tornadofx.App
import tornadofx.UIComponent

class UserlandUtility : App(UserlandUtilityView::class){
    override fun onBeforeShow(view: UIComponent) {
        try {
            GlobalScreen.registerNativeHook()
        } catch (ex: NativeHookException) {
            println("Could not initiate global hook")
        }
    }

    override fun stop() {
        GlobalScreen.unregisterNativeHook()
        super.stop()
    }

    companion object {
        fun main(args: Array<String>) {
            launch(UserlandUtility::class.java, *args)
        }
    }
}