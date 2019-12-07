package net.androidweekly.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import net.androidweekly.data.daos.issues.LocalIssuesDao
import net.androidweekly.data.daos.jobs.LocalJobsDao
import net.androidweekly.data.models.issues.Issue
import net.androidweekly.data.models.jobs.Job

/**
 * Project: Android Weekly
 * Created: Nov 23, 2019
 *
 * @author Mohamed Hamdan
 */
@Database(entities = [Job::class, Issue::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun localJobsDao(): LocalJobsDao

    abstract fun localIssuesDao(): LocalIssuesDao
}
