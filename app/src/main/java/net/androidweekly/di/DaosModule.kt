package net.androidweekly.di

import dagger.Module
import dagger.Provides
import net.androidweekly.core.qualifiers.Html
import net.androidweekly.core.qualifiers.Xml
import net.androidweekly.data.daos.issues.LocalIssuesDao
import net.androidweekly.data.daos.issues.RemoteIssuesDao
import net.androidweekly.data.daos.jobs.LocalJobsDao
import net.androidweekly.data.daos.jobs.RemoteJobsDao
import net.androidweekly.data.database.Database
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
@Module
object DaosModule {

    @Xml
    @Provides
    @Singleton
    fun provideRemoteIssuesDao(@Xml retrofit: Retrofit): RemoteIssuesDao {
        return retrofit.create(RemoteIssuesDao::class.java)
    }

    @Html
    @Provides
    @Singleton
    fun provideHtmlRemoteIssuesDao(@Html retrofit: Retrofit): RemoteIssuesDao {
        return retrofit.create(RemoteIssuesDao::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteJobsDao(@Html retrofit: Retrofit): RemoteJobsDao {
        return retrofit.create(RemoteJobsDao::class.java)
    }

    @Provides
    @Singleton
    fun provideLocalJobsDao(database: Database): LocalJobsDao {
        return database.localJobsDao()
    }

    @Provides
    @Singleton
    fun provideLocalIssuesDao(database: Database): LocalIssuesDao {
        return database.localIssuesDao()
    }
}
