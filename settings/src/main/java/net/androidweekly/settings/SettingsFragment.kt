package net.androidweekly.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference

/**
 * Project: Android Weekly
 * Created: Dec 13, 2019
 *
 * @author Mohamed Hamdan
 */
class SettingsFragment : PreferenceFragmentCompat() {

    private val switchTabsAnimations by lazy {
        findPreference<SwitchPreference>("key_fragment_settings_main_tabs_animations")
    }
    private val switchFabAnimations by lazy {
        findPreference<SwitchPreference>("key_fragment_settings_main_fab_animations")
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.fragment_settings, rootKey)

        switchFabAnimations?.isEnabled = switchTabsAnimations?.isChecked == true
        switchTabsAnimations?.setOnPreferenceChangeListener { _, newValue ->
            switchFabAnimations?.isEnabled = newValue as Boolean
            return@setOnPreferenceChangeListener true
        }
    }
}
