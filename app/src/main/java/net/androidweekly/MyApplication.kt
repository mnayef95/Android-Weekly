package net.androidweekly

import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import net.androidweekly.data.binding.BindingAdapters
import net.androidweekly.di.DaggerAppComponent
import javax.inject.Inject

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
class MyApplication : DaggerApplication() {

    @Inject
    lateinit var bindingAdapters: BindingAdapters

    override fun onCreate() {
        super.onCreate()
        DataBindingUtil.setDefaultComponent(object : DataBindingComponent {

            override fun getBindingAdapters(): BindingAdapters {
                return this@MyApplication.bindingAdapters
            }
        })
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
            .apply {
                inject(this@MyApplication)
            }
    }
}
