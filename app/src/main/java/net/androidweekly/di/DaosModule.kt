package net.androidweekly.di

import dagger.Module
import dagger.Provides
import net.androidweekly.core.qualifiers.Html
import net.androidweekly.core.qualifiers.Xml
import net.androidweekly.data.daos.issues.RemoteIssuesDao
import net.androidweekly.data.daos.jobs.LocalJobsDao
import net.androidweekly.data.daos.jobs.RemoteJobsDao
import net.androidweekly.data.database.Database
import retrofit2.Retrofit

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
    @JvmStatic
    fun provideRemoteIssuesDao(@Xml retrofit: Retrofit): RemoteIssuesDao {
        return retrofit.create(RemoteIssuesDao::class.java)
    }

    @Html
    @Provides
    @JvmStatic
    fun provideHtmlRemoteIssuesDao(@Html retrofit: Retrofit): RemoteIssuesDao {
        return retrofit.create(RemoteIssuesDao::class.java)
    }

    @Provides
    @JvmStatic
    fun provideRemoteJobsDao(@Html retrofit: Retrofit): RemoteJobsDao {
        return retrofit.create(RemoteJobsDao::class.java)
    }

    @Provides
    @JvmStatic
    fun provideLocalJobsDao(database: Database): LocalJobsDao {
        return database.localJobsDao()
    }
}
