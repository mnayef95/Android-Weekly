package net.androidweekly.data.daos.issues

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.androidweekly.data.models.issues.Issue

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
@Dao
interface LocalIssuesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(issue: Issue?)

    @Query("SELECT * FROM issues LIMIT 1")
    suspend fun getLatestIssues(): Issue?

    @Query("SELECT * FROM issues")
    suspend fun getPastIssues(): List<Issue>?
}
