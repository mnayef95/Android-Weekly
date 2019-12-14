package net.androidweekly.main

import android.net.Uri
import android.util.SparseArray

/**
 * Project: Android Weekly
 * Created: Dec 14, 2019
 *
 * @author Mohamed Hamdan
 */
object Urls {

    val urls by lazy {
        SparseArray<Uri>(URLS_INITIAL_CAPACITY).apply {
            put(R.id.item_menu_activity_main_navigation_view_about, ABOUT_URI)
            put(R.id.item_menu_activity_main_navigation_view_imprint, IMPRINT_URI)
            put(R.id.item_menu_activity_main_navigation_view_privacy, PRIVACY_URI)
            put(R.id.item_menu_activity_main_navigation_view_photo_editor_for_android, PHOTO_EDITOR_FOR_ANDROID_URI)
            put(R.id.item_menu_activity_main_navigation_view_pspdfkit, PSPDF_KIT_URI)
            put(R.id.item_menu_activity_main_navigation_view_twitter, TWITTER_URI)
            put(R.id.item_menu_activity_main_navigation_view_facebook, FACEBOOK_URI)
        }
    }

    private const val URLS_INITIAL_CAPACITY = 8

    val SUBSCRIBE_URI: Uri = Uri.parse("https://androidweekly.net/")
    val CONTACT_US_URI: Uri = Uri.parse("mailto:contact@androidweekly.net")

    private val ABOUT_URI = Uri.parse("https://androidweekly.net/about")
    private val IMPRINT_URI = Uri.parse("https://androidweekly.net/imprint")
    private val PRIVACY_URI = Uri.parse("https://androidweekly.net/privacy")
    private val PHOTO_EDITOR_FOR_ANDROID_URI = Uri.parse("https://photoeditorsdk.com")
    private val PSPDF_KIT_URI = Uri.parse("https://pspdfkit.com")
    private val TWITTER_URI = Uri.parse("https://twitter.com/androidweekly")
    private val FACEBOOK_URI = Uri.parse("https://www.facebook.com/androidweekly.net")
}
