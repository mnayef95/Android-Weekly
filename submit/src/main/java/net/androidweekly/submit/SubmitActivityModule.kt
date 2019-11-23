package net.androidweekly.submit

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
@Module
abstract class SubmitActivityModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideViewModel(
            activity: AppCompatActivity,
            factory: SubmitViewModelFactory
        ): SubmitViewModel {
            return ViewModelProvider(activity, factory)[SubmitViewModel::class.java]
        }
    }

    @Binds
    abstract fun bindActivity(activity: SubmitActivity): AppCompatActivity
}
