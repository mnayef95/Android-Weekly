package net.androidweekly.data.repositories.jobs

import net.androidweekly.data.models.jobs.JobsWrapper

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
interface JobsRepository {

    suspend fun getAllJobs(): JobsWrapper
}
