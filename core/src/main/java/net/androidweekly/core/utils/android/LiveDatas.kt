package net.androidweekly.core.utils.android

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
inline fun <T> LiveData<T>.observe(owner: LifecycleOwner, crossinline callback: (T) -> Unit) {
    observe(owner, Observer { callback(it) })
}
