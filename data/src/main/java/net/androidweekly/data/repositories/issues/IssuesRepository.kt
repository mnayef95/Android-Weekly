package net.androidweekly.data.repositories.issues

import net.androidweekly.data.models.issues.AuthenticityTokens
import net.androidweekly.data.models.issues.IssueWrapper

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
interface IssuesRepository {

    suspend fun getLatestIssue(): IssueWrapper

    suspend fun getAuthenticityTokens(): AuthenticityTokens

    suspend fun submitLink(link: String?, token: String?)

    suspend fun submitConference(link: String?, token: String?)
}
