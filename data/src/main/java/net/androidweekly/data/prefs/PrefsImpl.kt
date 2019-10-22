package net.androidweekly.data.prefs

import android.content.SharedPreferences

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
class PrefsImpl(private val sharedPreferences: SharedPreferences) : Prefs {

    override var isFingerprintActivated: Boolean
        get() = sharedPreferences.getBoolean(IS_FINGERPRINT_ACTIVATED, false)
        set(value) {
            sharedPreferences.edit().putBoolean(IS_FINGERPRINT_ACTIVATED, value).apply()
        }

    companion object {

        const val IS_FINGERPRINT_ACTIVATED = "isFingerprintActivated"
    }
}
