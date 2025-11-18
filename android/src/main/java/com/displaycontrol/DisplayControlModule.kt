package com.displaycontrol

import android.util.Log
import android.view.WindowManager
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.annotations.ReactModule
import com.facebook.fbreact.specs.NativeDisplayControlSpec

@ReactModule(name = DisplayControlModule.NAME)
class DisplayControlModule(
    reactContext: ReactApplicationContext,
) : NativeDisplayControlSpec(reactContext) {

    override fun getName(): String = NAME

    override fun activate() {
        Log.d(NAME, "Activating KeepAwake")

        val activity = currentActivity ?: run {
            Log.w(NAME, "activate() called but currentActivity is null")
            return
        }

        activity.runOnUiThread {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

    override fun deactivate() {
        Log.d(NAME, "Deactivating KeepAwake")

        val activity = currentActivity ?: run {
            Log.w(NAME, "deactivate() called but currentActivity is null")
            return
        }

        activity.runOnUiThread {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

    override fun setBrightness(value: Double) {
        val activity = currentActivity ?: run {
            Log.w(NAME, "setBrightness() called but currentActivity is null")
            return
        }

        activity.runOnUiThread {
            val window = activity.window
            val lp = window.attributes

            val brightness = if (value < 0) {
                // -1f = dùng theo hệ thống
                -1f
            } else {
                value.toFloat().coerceIn(0f, 1f)
            }

            lp.screenBrightness = brightness
            window.attributes = lp
            Log.d(NAME, "Brightness set to $brightness")
        }
    }

    override fun resetBrightness() {
        val activity = currentActivity ?: run {
            Log.w(NAME, "resetBrightness() called but currentActivity is null")
            return
        }

        activity.runOnUiThread {
            val window = activity.window
            val lp = window.attributes
            lp.screenBrightness = -1f
            window.attributes = lp
            Log.d(NAME, "Brightness reset to system default")
        }
    }

    companion object {
        const val NAME = "DisplayControl"
    }
}
