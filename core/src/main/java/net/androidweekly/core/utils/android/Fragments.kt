package net.androidweekly.core.utils.android

import androidx.navigation.NavOptions
import net.androidweekly.core.R

/**
 * Project: Android Weekly
 * Created: Dec 13, 2019
 *
 * @author Mohamed Hamdan
 */
fun fadeAnimationNavOptions(): NavOptions = NavOptions.Builder()
    .setEnterAnim(R.anim.nav_default_enter_anim)
    .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
    .setExitAnim(R.anim.nav_default_exit_anim)
    .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
    .build()
