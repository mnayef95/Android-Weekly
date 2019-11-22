package net.androidweekly.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import net.androidweekly.core.activities.BaseToolbarActivity
import net.androidweekly.core.custom.views.CustomBottomAppBar
import net.androidweekly.core.utils.android.bindView
import javax.inject.Inject

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
class MainActivity : BaseToolbarActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var customTabsIntent: CustomTabsIntent

    private val navController by lazy { findNavController(R.id.fragment_activity_main_navigation) }
    private val bottomNavigationView: BottomNavigationView? by bindView(R.id.bottom_navigation_view_activity_home)
    private val bottomAppBar: CustomBottomAppBar? by bindView(R.id.bottom_app_bar_activity_main)
    private val drawerLayoutRoot: DrawerLayout? by bindView(R.id.drawer_layout_activity_main_root)
    private val navigationView: NavigationView? by bindView(R.id.navigation_view_activity_main)
    private val navigationViewHeader: View? by lazy { navigationView?.getHeaderView(0) }
    private var buttonSubscribe: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSubscribe = navigationViewHeader?.findViewById(R.id.button_header_activity_main_navigation_view_subscribe)
        bottomAppBar?.onSlideDown { it?.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END }
        bottomAppBar?.onSlideUp { it?.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(drawerLayoutRoot) || super.onSupportNavigateUp()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initNavigation()
        initDrawerLayout()
        initListeners()
    }

    private fun initNavigation() {
        bottomNavigationView?.setupWithNavController(navController)
        toolbar?.setupWithNavController(navController, drawerLayoutRoot)
        toolbar?.setNavigationOnClickListener { onSupportNavigateUp() }
    }

    private fun initDrawerLayout() {
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayoutRoot,
            toolbar,
            R.string.app_name,
            R.string.app_name
        )
        drawerLayoutRoot?.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    private fun initListeners() {
        buttonSubscribe?.setOnClickListener {
            onSubscribeButtonClicked()
        }
        navigationView?.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_menu_activity_main_navigation_view_about -> {
                    onAboutSelected()
                }
                R.id.item_menu_activity_main_navigation_view_contact_us -> {
                    onContactUsSelected()
                }
                R.id.item_menu_activity_main_navigation_view_imprint -> {
                    onImprintSelected()
                }
                R.id.item_menu_activity_main_navigation_view_privacy -> {
                    onPrivacySelected()
                }
                R.id.item_menu_activity_main_navigation_view_photo_editor_for_android -> {
                    onPhotoEditorForAndroidSelected()
                }
                R.id.item_menu_activity_main_navigation_view_pspdfkit -> {
                    onPspdfKitSelected()
                }
                R.id.item_menu_activity_main_navigation_view_twitter -> {
                    onTwitterSelected()
                }
                R.id.item_menu_activity_main_navigation_view_facebook -> {
                    onFacebookSelected()
                }
            }
            return@setNavigationItemSelectedListener false
        }
    }

    private fun onSubscribeButtonClicked() {
        customTabsIntent.launchUrl(this, SUBSCRIBE_URI)
    }

    private fun onAboutSelected() {
        customTabsIntent.launchUrl(this, ABOUT_URI)
    }

    private fun onContactUsSelected() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = CONTACT_US_URI
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, R.string.message_email_component_not_found, Toast.LENGTH_SHORT).show()
        }
    }

    private fun onImprintSelected() {
        customTabsIntent.launchUrl(this, IMPRINT_URI)
    }

    private fun onPrivacySelected() {
        customTabsIntent.launchUrl(this, PRIVACY_URI)
    }

    private fun onPhotoEditorForAndroidSelected() {
        customTabsIntent.launchUrl(this, PHOTO_EDITOR_FOR_ANDROID_URI)
    }

    private fun onPspdfKitSelected() {
        customTabsIntent.launchUrl(this, PSPDF_KIT_URI)
    }

    private fun onTwitterSelected() {
        customTabsIntent.launchUrl(this, TWITTER_URI)
    }

    private fun onFacebookSelected() {
        customTabsIntent.launchUrl(this, FACEBOOK_URI)
    }

    override fun onBackPressed() {
        if (drawerLayoutRoot?.isDrawerOpen(GravityCompat.START) == true) {
            drawerLayoutRoot?.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private companion object {

        private val SUBSCRIBE_URI = Uri.parse("https://androidweekly.net/")
        private val ABOUT_URI = Uri.parse("https://androidweekly.net/about")
        private val CONTACT_US_URI = Uri.parse("mailto:contact@androidweekly.net")
        private val IMPRINT_URI = Uri.parse("https://androidweekly.net/imprint")
        private val PRIVACY_URI = Uri.parse("https://androidweekly.net/privacy")
        private val PHOTO_EDITOR_FOR_ANDROID_URI = Uri.parse("https://photoeditorsdk.com")
        private val PSPDF_KIT_URI = Uri.parse("https://pspdfkit.com")
        private val TWITTER_URI = Uri.parse("https://twitter.com/androidweekly")
        private val FACEBOOK_URI = Uri.parse("https://www.facebook.com/androidweekly.net")
    }
}
