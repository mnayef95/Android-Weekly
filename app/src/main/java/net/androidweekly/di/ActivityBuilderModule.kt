package net.androidweekly.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.androidweekly.main.MainActivity
import net.androidweekly.main.MainActivityModule

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
}
