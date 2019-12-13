package net.androidweekly.core.custom.views

import android.content.Context
import android.content.SharedPreferences
import android.util.AttributeSet
import androidx.preference.PreferenceManager
import com.google.android.material.R
import com.google.android.material.bottomappbar.BottomAppBar

/**
 * Project: Android Weekly
 * Created: Oct 21, 2019
 *
 * @author Mohamed Hamdan
 */
class CustomBottomAppBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.bottomAppBarStyle
) : BottomAppBar(context, attrs, defStyleAttr) {

    private var sharedPreferences: SharedPreferences? = null
    private val isFabAnimationEnabled: Boolean
        get() {
            return sharedPreferences?.getBoolean(KEY_FAB_ANIMATION_ENABLED, true) ?: true
        }
    private val isTabsAnimationEnabled: Boolean
        get() {
            return sharedPreferences?.getBoolean(KEY_TABS_ANIMATION_ENABLED, true) ?: true
        }

    private var onSlideDown: ((child: BottomAppBar?) -> Unit)? = null
    private var onSlideUp: ((child: BottomAppBar?) -> Unit)? = null
    private var behavior: Behavior? = null

    init {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    override fun getBehavior(): Behavior {
        if (behavior == null) {
            behavior = BottomAppBarBehavior()
        }
        return behavior!!
    }

    fun onSlideDown(listener: (BottomAppBar?) -> Unit) {
        onSlideDown = { listener(it) }
    }

    fun onSlideUp(listener: (BottomAppBar?) -> Unit) {
        onSlideUp = { listener(it) }
    }

    fun slideUp() {
        behavior?.slideUp(this)
    }

    private inner class BottomAppBarBehavior : Behavior() {

        override fun slideDown(child: BottomAppBar) {
            if (isTabsAnimationEnabled) {
                super.slideDown(child)
            }

            if (isFabAnimationEnabled && isTabsAnimationEnabled) {
                onSlideDown?.invoke(child)
            }
        }

        override fun slideUp(child: BottomAppBar) {
            if (isTabsAnimationEnabled) {
                super.slideUp(child)
            }

            if (isFabAnimationEnabled && isTabsAnimationEnabled) {
                onSlideUp?.invoke(child)
            }
        }
    }

    private companion object {

        private const val KEY_FAB_ANIMATION_ENABLED = "key_fragment_settings_main_fab_animations"
        private const val KEY_TABS_ANIMATION_ENABLED = "key_fragment_settings_main_tabs_animations"
    }
}
