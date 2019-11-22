package net.androidweekly.data.repositories.issues

import net.androidweekly.data.daos.issues.RemoteIssuesDao
import net.androidweekly.data.models.issues.IssueWrapper
import javax.inject.Inject

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
class IssuesRepositoryImpl @Inject constructor(
    private val remoteIssuesDao: RemoteIssuesDao
) : IssuesRepository {

    override suspend fun getLatestIssue(): IssueWrapper {
        val data = remoteIssuesDao.getAllIssues()
        return IssueWrapper()
    }
}
