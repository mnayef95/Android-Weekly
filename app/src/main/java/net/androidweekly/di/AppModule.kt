package net.androidweekly.di

import android.content.Context
import dagger.Binds
import dagger.Module
import net.androidweekly.MyApplication

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
@Module
abstract class AppModule {

    @Binds
    abstract fun provideApplication(application: MyApplication): Context
}
