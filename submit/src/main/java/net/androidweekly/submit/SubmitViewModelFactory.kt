package net.androidweekly.submit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.androidweekly.core.qualifiers.Html
import net.androidweekly.data.repositories.issues.IssuesRepository
import javax.inject.Inject

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
class SubmitViewModelFactory @Inject constructor(

    @Html
    private val issuesRepository: IssuesRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SubmitViewModel(issuesRepository) as T
    }
}
