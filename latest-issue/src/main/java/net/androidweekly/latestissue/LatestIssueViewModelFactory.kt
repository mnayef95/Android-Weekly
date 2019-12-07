package net.androidweekly.latestissue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.androidweekly.core.qualifiers.Xml
import net.androidweekly.data.repositories.issues.IssuesRepository
import javax.inject.Inject

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
class LatestIssueViewModelFactory @Inject constructor(

    @Xml
    private val issuesRepository: IssuesRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LatestIssueViewModel(issuesRepository) as T
    }
}
