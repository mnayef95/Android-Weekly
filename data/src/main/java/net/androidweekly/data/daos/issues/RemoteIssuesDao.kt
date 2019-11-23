package net.androidweekly.data.daos.issues

import net.androidweekly.data.models.issues.AuthenticityTokens
import net.androidweekly.data.models.issues.IssueRss
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
interface RemoteIssuesDao {

    @GET("feed?u=887caf4f48db76fd91e20a06d&id=4eb677ad19")
    suspend fun getAllIssues(): IssueRss

    @GET("/")
    suspend fun getAuthenticityToken(): AuthenticityTokens

    @FormUrlEncoded
    @POST("/links")
    suspend fun submit(@FieldMap body: Map<String, String?>)
}
