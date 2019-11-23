package net.androidweekly.di

import dagger.Module
import dagger.Provides
import net.androidweekly.data.daos.issues.RemoteIssuesDao
import net.androidweekly.data.daos.jobs.LocalJobsDao
import net.androidweekly.data.daos.jobs.RemoteJobsDao
import net.androidweekly.data.database.Database
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

    @Provides
    @JvmStatic
    fun provideLocalJobsDao(database: Database): LocalJobsDao {
        return database.localJobsDao()
    }
}
