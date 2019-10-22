package net.androidweekly.core.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.ContentFrameLayout
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
@Suppress("UNUSED_PARAMETER")
abstract class BaseActivity : DaggerAppCompatActivity() {

    var parentView: View? = null

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        findParentView()
    }

    private fun findParentView() {
        val content = findViewById<View>(android.R.id.content)
        if (content is ContentFrameLayout && content.childCount > 0) {
            parentView = content.getChildAt(0)
        }
        if (parentView == null) {
            parentView = content
        }
    }

    // This injection is a workaround for a known issue in Gradle build system https://github.com/google/dagger/issues/955
    @Inject
    fun setDummy(dummy: String) {
        // No impl
    }
}
