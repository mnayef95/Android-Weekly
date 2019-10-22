package net.androidweekly.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.androidweekly.core.Activities
import net.androidweekly.core.intentTo

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(intentTo(Activities.Main))

        finish()
    }

    override fun onBackPressed() {
        // No impl
    }
}
