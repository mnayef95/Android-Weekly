package net.androidweekly.pastissue

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by Furkan on 2019-12-09
 */

@Module
abstract class PastIssueFragmentModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideViewModel(
            fragment: Fragment,
            factory: PastIssueViewModelFactory
        ): PastIssueViewModel {
            return ViewModelProvider(fragment, factory)[PastIssueViewModel::class.java]
        }
    }

    @Binds
    abstract fun bindActivity(activity: PastIssueFragment): Fragment
}
