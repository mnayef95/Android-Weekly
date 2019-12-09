package net.androidweekly.data.repositories.issues

import net.androidweekly.data.models.issues.AuthenticityTokens
import net.androidweekly.data.models.issues.Issue

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
interface IssuesRepository {

    suspend fun getRemoteLatestIssue(): Issue?

    suspend fun getLocalLatestIssues(): Issue?

    suspend fun getRemotePastIssues(): List<Issue>?

    suspend fun getLocalPastIssues(): List<Issue>?

    suspend fun getAuthenticityTokens(): AuthenticityTokens

    suspend fun submitLink(link: String?, token: String?)

    suspend fun submitConference(link: String?, token: String?)
}
