package net.androidweekly.data.repositories.jobs

import net.androidweekly.data.daos.jobs.LocalJobsDao
import net.androidweekly.data.daos.jobs.RemoteJobsDao
import net.androidweekly.data.models.jobs.Job
import javax.inject.Inject

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
class JobsRepositoryImpl @Inject constructor(
    private val remoteJobsDao: RemoteJobsDao,
    private val localJobsDao: LocalJobsDao
) : JobsRepository {

    override suspend fun getRemoteJobs(): List<Job>? {
        val jobs = remoteJobsDao.getAllJobs()
        localJobsDao.insert(jobs.jobs)

        return jobs.jobs
    }

    override suspend fun getLocalJobs(): List<Job>? {
        return localJobsDao.getJobs()
    }
}
