package net.androidweekly.core.utils.android

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

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

@Deprecated(
    message = "Please don't use this delegate, use findViewById instead",
    replaceWith = ReplaceWith("findViewById()"),
    level = DeprecationLevel.WARNING
)
fun <T : View> Fragment.bindView(@IdRes res: Int): Lazy<T?> {
    return lazy(LazyThreadSafetyMode.NONE) { view?.findViewById<T>(res) }
}

@Deprecated(
    message = "Please don't use this delegate, use findViewById instead",
    replaceWith = ReplaceWith("findViewById()"),
    level = DeprecationLevel.WARNING
)
fun <T : View> View.bindView(@IdRes res: Int): Lazy<T?> {
    return lazy(LazyThreadSafetyMode.NONE) { findViewById<T>(res) }
}
