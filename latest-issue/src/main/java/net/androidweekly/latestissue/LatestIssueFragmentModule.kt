package net.androidweekly.latestissue

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
abstract class LatestIssueFragmentModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideViewModel(
            fragment: Fragment,
            factory: LatestIssueViewModelFactory
        ): LatestIssueViewModel {
            return ViewModelProvider(fragment, factory)[LatestIssueViewModel::class.java]
        }
    }

    @Binds
    abstract fun bindActivity(activity: LatestIssueFragment): Fragment
}
