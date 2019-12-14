package net.androidweekly.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import net.androidweekly.core.activities.BaseToolbarActivity
import net.androidweekly.core.custom.views.CustomBottomAppBar
import net.androidweekly.core.utils.android.bindView
import net.androidweekly.submit.SubmitActivity
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
    private val floatingActionButtonSubmit: FloatingActionButton? by bindView(
        R.id.floating_action_button_activity_main_submit
    )
    private val navigationViewHeader: View? by lazy { navigationView?.getHeaderView(0) }
    private var buttonSubscribe: Button? = null

    private val itemSubmit by lazy { bottomNavigationView?.menu?.findItem(R.id.item_menu_activity_main_submit) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSubscribe = navigationViewHeader?.findViewById(R.id.button_header_activity_main_navigation_view_subscribe)
        bottomAppBar?.onSlideDown { it?.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END }
        bottomAppBar?.onSlideUp { it?.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomAppBar?.slideUp()

            if (destination.id != R.id.item_menu_activity_main_settings) {
                navigationView?.menu?.findItem(R.id.item_menu_activity_main_settings)?.isChecked = false
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(drawerLayoutRoot) || super.onSupportNavigateUp()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initNavigation()
        initListeners()
    }

    private fun initNavigation() {
        navigationView?.setupWithNavController(navController)
        setupActionBarWithNavController(navController, drawerLayoutRoot)
        toolbar?.setNavigationOnClickListener { onSupportNavigateUp() }
        bottomNavigationView?.setupWithNavController(navController)
    }

    private fun initListeners() {
        buttonSubscribe?.setOnClickListener {
            onSubscribeButtonClicked()
        }

        floatingActionButtonSubmit?.setOnClickListener { SubmitActivity.start(this, it) }

        itemSubmit?.setOnMenuItemClickListener {
            floatingActionButtonSubmit?.callOnClick()
            return@setOnMenuItemClickListener false
        }

        initNavigationViewListener()
    }

    private fun initNavigationViewListener() {
        navigationView?.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_menu_activity_main_navigation_view_contact_us -> {
                    onContactUsSelected()
                }
                R.id.item_menu_activity_main_settings -> {
                    navController.navigate(R.id.item_menu_activity_main_settings)
                    onBackPressed()
                    return@setNavigationItemSelectedListener true
                }
                else -> {
                    customTabsIntent.launchUrl(this, Urls.urls[it.itemId])
                }
            }
            return@setNavigationItemSelectedListener false
        }
    }

    private fun onSubscribeButtonClicked() {
        customTabsIntent.launchUrl(this, Urls.SUBSCRIBE_URI)
    }

    private fun onContactUsSelected() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Urls.CONTACT_US_URI
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, R.string.message_email_component_not_found, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        if (drawerLayoutRoot?.isDrawerOpen(GravityCompat.START) == true) {
            drawerLayoutRoot?.closeDrawer(GravityCompat.START)
        } else if (!navController.popBackStack()) {
            super.onBackPressed()
        }
    }
}
