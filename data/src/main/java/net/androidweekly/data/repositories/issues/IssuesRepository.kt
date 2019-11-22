package net.androidweekly.data.repositories.issues

import net.androidweekly.data.models.issues.IssueWrapper

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
interface IssuesRepository {

    suspend fun getLatestIssue(): IssueWrapper
}
