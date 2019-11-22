package net.androidweekly.jobs

import androidx.fragment.app.Fragment
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
abstract class JobsFragmentModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideViewModel(
            fragment: Fragment,
            factory: JobsViewModelFactory
        ): JobsViewModel {
            return ViewModelProvider(fragment, factory)[JobsViewModel::class.java]
        }
    }

    @Binds
    abstract fun bindActivity(activity: JobsFragment): Fragment
}
