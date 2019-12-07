package net.androidweekly.data.repositories.issues

import net.androidweekly.data.daos.issues.LocalIssuesDao
import net.androidweekly.data.daos.issues.RemoteIssuesDao
import net.androidweekly.data.models.issues.AuthenticityTokens
import net.androidweekly.data.models.issues.Issue

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
class IssuesRepositoryImpl(
    private val remoteIssuesDao: RemoteIssuesDao,
    private val localIssuesDao: LocalIssuesDao
) : IssuesRepository {

    override suspend fun getRemoteLatestIssue(): Issue? {
        val latestIssue = getPastIssues()?.first()
        localIssuesDao.insert(latestIssue)
        return latestIssue
    }

    override suspend fun getLocalLatestIssues(): Issue? {
        return localIssuesDao.getLatestIssues()
    }

    override suspend fun getPastIssues(): List<Issue>? {
        return remoteIssuesDao.getAllIssues().channel?.items
    }

    override suspend fun getAuthenticityTokens(): AuthenticityTokens {
        return remoteIssuesDao.getAuthenticityToken()
    }

    override suspend fun submitLink(link: String?, token: String?) {
        val body = mapOf(
            "link[url]" to link,
            "utf8" to "&#x2713;",
            "authenticity_token" to token,
            "link[human]" to ""
        )
        remoteIssuesDao.submit(body)
    }

    override suspend fun submitConference(link: String?, token: String?) {
        val body = mapOf(
            "link[url]" to link,
            "utf8" to "&#x2713;",
            "authenticity_token" to token,
            "link[conference]" to "1",
            "link[human]" to ""
        )
        remoteIssuesDao.submit(body)
    }
}
