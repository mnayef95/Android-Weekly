package net.androidweekly.data.daos.issues

import net.androidweekly.data.models.issues.IssueRss
import retrofit2.http.GET

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
interface RemoteIssuesDao {

    @GET("https://us2.campaign-archive.com/feed?u=887caf4f48db76fd91e20a06d&id=4eb677ad19")
    suspend fun getAllIssues(): IssueRss
}
