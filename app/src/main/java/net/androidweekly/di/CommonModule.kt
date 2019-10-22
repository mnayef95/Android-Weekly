package net.androidweekly.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import io.paperdb.Book
import io.paperdb.Paper
import net.androidweekly.data.CoroutinesContextProvider
import net.androidweekly.data.CoroutinesContextProviderImpl
import net.androidweekly.data.binding.BindingAdapters
import net.androidweekly.data.binding.BindingAdaptersImpl
import net.androidweekly.data.prefs.Prefs
import net.androidweekly.data.prefs.PrefsImpl
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

    // This injection is a workaround for a known issue in Gradle build system https://github.com/google/dagger/issues/955
    @JvmStatic
    @Singleton
    @Provides
    fun provideDummyObject(): String {
        return ""
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideContextProvider(): CoroutinesContextProvider {
        return CoroutinesContextProviderImpl()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideBook(): Book {
        return Paper.book()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideSharedPreferences(context: Application): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun providePrefs(sharedPreferences: SharedPreferences): Prefs {
        return PrefsImpl(sharedPreferences)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideBindingAdapters(): BindingAdapters {
        return BindingAdaptersImpl()
    }
}
