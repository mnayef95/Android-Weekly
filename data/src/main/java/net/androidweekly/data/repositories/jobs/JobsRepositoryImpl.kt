package net.androidweekly.data.repositories.jobs

import net.androidweekly.data.daos.jobs.RemoteJobsDao
import net.androidweekly.data.models.jobs.JobsWrapper
import javax.inject.Inject

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
class JobsRepositoryImpl @Inject constructor(
    private val remoteJobsDao: RemoteJobsDao
) : JobsRepository {

    override suspend fun getAllJobs(): JobsWrapper {
        return remoteJobsDao.getAllJobs()
    }
}
