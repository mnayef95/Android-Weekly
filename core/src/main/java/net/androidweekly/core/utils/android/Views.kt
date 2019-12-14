package net.androidweekly.core.utils.android

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
@Deprecated(
    message = "Please don't use this delegate, use findViewById instead",
    replaceWith = ReplaceWith("findViewById()"),
    level = DeprecationLevel.WARNING
)
fun <T : View> Activity.bindView(@IdRes res: Int): Lazy<T?> {
    return lazy(LazyThreadSafetyMode.NONE) { findViewById<T>(res) }
}
