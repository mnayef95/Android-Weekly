package net.androidweekly.di

import dagger.Module
import dagger.Provides
import net.androidweekly.data.daos.issues.RemoteIssuesDao
import net.androidweekly.data.daos.jobs.RemoteJobsDao
import retrofit2.Retrofit
import javax.inject.Named

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
@Module
object DaosModule {

    @Provides
    @JvmStatic
    fun provideRemoteIssuesDao(@Named(NetworkModule.XML_RETROFIT) retrofit: Retrofit): RemoteIssuesDao {
        return retrofit.create(RemoteIssuesDao::class.java)
    }

    @Provides
    @JvmStatic
    fun provideRemoteJobsDao(@Named(NetworkModule.HTML_RETROFIT) retrofit: Retrofit): RemoteJobsDao {
        return retrofit.create(RemoteJobsDao::class.java)
    }
}
