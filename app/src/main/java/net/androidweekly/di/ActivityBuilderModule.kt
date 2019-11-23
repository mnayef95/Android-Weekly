package net.androidweekly.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.androidweekly.main.MainActivity
import net.androidweekly.main.MainActivityModule
import net.androidweekly.submit.SubmitActivity
import net.androidweekly.submit.SubmitActivityModule

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [SubmitActivityModule::class])
    abstract fun bindSubmitActivity(): SubmitActivity
}
