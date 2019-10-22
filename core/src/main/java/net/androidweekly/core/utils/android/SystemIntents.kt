@file:JvmName("SystemIntents")

package net.androidweekly.core.utils.android

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
fun Context?.startDialIntent(phone: String?) {
    val intent = Intent(Intent.ACTION_DIAL)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    intent.data = Uri.parse("tel:$phone")
    this?.startActivity(intent)
}

fun Context?.startMapIntent(latitude: Double?, longitude: Double?, dealershipName: String?) {
    val gmmIntentUri = Uri.parse("geo:0,0?q=$latitude,$longitude($dealershipName)")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    this?.packageManager?.let { packageManager ->
        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        }
    }
}

fun Context?.startAppSettingsIntent() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", this?.packageName, null)
    intent.data = uri
    this?.packageManager?.let { packageManager ->
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}

fun Context?.startAppDetailsOnGooglePlay() {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse("market://details?id=${this?.packageName}")
    this?.packageManager?.let { packageManager ->
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}
