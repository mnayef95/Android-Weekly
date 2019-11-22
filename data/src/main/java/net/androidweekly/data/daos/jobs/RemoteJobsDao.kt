package net.androidweekly.data.daos.jobs

import net.androidweekly.data.models.jobs.JobsWrapper
import retrofit2.http.GET

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
interface RemoteJobsDao {

    @GET("https://androidweekly.net/jobs")
    suspend fun getAllJobs(): JobsWrapper
}
