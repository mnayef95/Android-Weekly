package net.androidweekly.di

import dagger.Binds
import dagger.Module
import net.androidweekly.data.repositories.issues.IssuesRepository
import net.androidweekly.data.repositories.issues.IssuesRepositoryImpl
import net.androidweekly.data.repositories.jobs.JobsRepository
import net.androidweekly.data.repositories.jobs.JobsRepositoryImpl

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
@Module
abstract class RepositoriesModule {

    @Binds
    abstract fun provideIssuesRepository(issuesRepository: IssuesRepositoryImpl): IssuesRepository

    @Binds
    abstract fun provideJobsRepository(jobsRepository: JobsRepositoryImpl): JobsRepository
}
