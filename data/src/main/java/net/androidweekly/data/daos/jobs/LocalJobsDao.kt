package net.androidweekly.data.daos.jobs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.androidweekly.data.models.jobs.Job

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
@Dao
interface LocalJobsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(jobs: List<Job>?)

    @Query("SELECT * FROM jobs")
    suspend fun getJobs(): List<Job>?
}
