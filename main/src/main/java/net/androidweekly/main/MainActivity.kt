package net.androidweekly.main

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
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

    private val navController by lazy { findNavController(R.id.fragment_activity_main_navigation) }
    private val bottomNavigationView: BottomNavigationView? by bindView(R.id.bottom_navigation_view_activity_home)
    private val bottomAppBar: CustomBottomAppBar? by bindView(R.id.bottom_app_bar_activity_main)
    private val drawerLayoutRoot: DrawerLayout? by bindView(R.id.drawer_layout_activity_main_root)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    override fun onBackPressed() {
        if (drawerLayoutRoot?.isDrawerOpen(GravityCompat.START) == true) {
            drawerLayoutRoot?.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
