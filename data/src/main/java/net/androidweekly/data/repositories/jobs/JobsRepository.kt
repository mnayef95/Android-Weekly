package net.androidweekly.data.repositories.jobs

import net.androidweekly.data.models.jobs.Job

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
interface JobsRepository {

    suspend fun getRemoteJobs(): List<Job>?

    suspend fun getLocalJobs(): List<Job>?
}
