package net.androidweekly.pastissue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.androidweekly.core.qualifiers.Xml
import net.androidweekly.data.repositories.issues.IssuesRepository
import javax.inject.Inject

/**
 * Created by Furkan on 2019-12-09
 */

class PastIssueViewModelFactory @Inject constructor(

    @Xml
    private val issuesRepository: IssuesRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PastIssueViewModel(issuesRepository) as T
    }
}
