package net.androidweekly.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.room.Room
import dagger.Module
import dagger.Provides
import net.androidweekly.data.CoroutinesContextProvider
import net.androidweekly.data.CoroutinesContextProviderImpl
import net.androidweekly.data.binding.BindingAdapters
import net.androidweekly.data.binding.BindingAdaptersImpl
import net.androidweekly.data.database.Database
import net.androidweekly.data.prefs.Prefs
import net.androidweekly.data.prefs.PrefsImpl
import net.androidweekly.main.R
import javax.inject.Singleton

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
@Module
object CommonModule {

    private const val SHARED_PREFERENCES_NAME = "net.androidweekly"
    private const val DATABASE_NAME = "androidweekly"

    // This injection is a workaround for a known issue in Gradle build system https://github.com/google/dagger/issues/955
    @Provides
    @Singleton
    fun provideDummyObject(): String {
        return ""
    }

    @Provides
    @Singleton
    fun provideContextProvider(): CoroutinesContextProvider {
        return CoroutinesContextProviderImpl()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Application): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePrefs(sharedPreferences: SharedPreferences): Prefs {
        return PrefsImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideBindingAdapters(): BindingAdapters {
        return BindingAdaptersImpl()
    }

    @Provides
    @Singleton
    fun provideCustomTabsIntent(context: Application): CustomTabsIntent {
        return CustomTabsIntent.Builder()
            .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
            .build()
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Application): Database {
        return Room.databaseBuilder(context, Database::class.java, DATABASE_NAME).build()
    }
}
