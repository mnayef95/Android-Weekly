package net.androidweekly.core

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
fun Context.intentTo(addressableActivity: AddressableActivity): Intent {
    return Intent(addressableActivity.action).setPackage(packageName)
}

fun Fragment.intentTo(addressableActivity: AddressableActivity): Intent {
    return Intent(addressableActivity.action).setPackage(context?.packageName)
}

interface AddressableActivity {

    val action: String
}

object Activities {

    object Main : AddressableActivity {

        override val action: String = "net.androidweekly.main.MainActivity"

    }
}
