package net.androidweekly.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.androidweekly.latestissue.LatestIssueFragment
import net.androidweekly.latestissue.LatestIssueFragmentModule

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [LatestIssueFragmentModule::class])
    abstract fun bindLatestIssuesFragment(): LatestIssueFragment
}
