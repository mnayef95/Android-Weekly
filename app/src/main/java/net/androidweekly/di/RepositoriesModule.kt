package net.androidweekly.di

import dagger.Module
import dagger.Provides
import net.androidweekly.core.qualifiers.Html
import net.androidweekly.core.qualifiers.Xml
import net.androidweekly.data.daos.issues.RemoteIssuesDao
import net.androidweekly.data.daos.jobs.LocalJobsDao
import net.androidweekly.data.daos.jobs.RemoteJobsDao
import net.androidweekly.data.repositories.issues.IssuesRepository
import net.androidweekly.data.repositories.issues.IssuesRepositoryImpl
import net.androidweekly.data.repositories.jobs.JobsRepository
import net.androidweekly.data.repositories.jobs.JobsRepositoryImpl
import javax.inject.Singleton

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
@Module
class RepositoriesModule {

    @Xml
    @Provides
    @Singleton
    fun provideIssuesRepository(@Xml remoteIssuesDao: RemoteIssuesDao): IssuesRepository {
        return IssuesRepositoryImpl(remoteIssuesDao)
    }

    @Html
    @Provides
    @Singleton
    fun provideHtmlIssuesRepository(@Html remoteIssuesDao: RemoteIssuesDao): IssuesRepository {
        return IssuesRepositoryImpl(remoteIssuesDao)
    }

    @Provides
    @Singleton
    fun provideJobsRepository(remoteJobsDao: RemoteJobsDao, localJobsDao: LocalJobsDao): JobsRepository {
        return JobsRepositoryImpl(remoteJobsDao, localJobsDao)
    }
}
