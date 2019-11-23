package net.androidweekly.di

import dagger.Module
import dagger.Provides
import net.androidweekly.data.BuildConfig
import okhttp3.OkHttpClient
import pl.droidsonroids.retrofit2.JspoonConverterFactory
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import tech.linjiang.pandora.Pandora
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
@Module
object NetworkModule {

    const val HTML_RETROFIT = "htmlRetrofit"
    const val XML_RETROFIT = "xmlRetrofit"

    private const val TIMEOUT_MINUTES = 1L

    @JvmStatic
    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(Pandora.get().interceptor)
            .readTimeout(TIMEOUT_MINUTES, TimeUnit.MINUTES)
            .writeTimeout(TIMEOUT_MINUTES, TimeUnit.MINUTES)
            .connectTimeout(TIMEOUT_MINUTES, TimeUnit.MINUTES)
            .build()
    }

    @Named(XML_RETROFIT)
    @JvmStatic
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.ISSUES_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
    }

    @Named(HTML_RETROFIT)
    @JvmStatic
    @Provides
    @Singleton
    fun provideHtmlRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.JOBS_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(JspoonConverterFactory.create())
            .build()
    }
}
