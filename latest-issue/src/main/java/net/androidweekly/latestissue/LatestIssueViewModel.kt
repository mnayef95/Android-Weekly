package net.androidweekly.latestissue

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.androidweekly.core.BaseViewModel
import net.androidweekly.data.CoroutinesContextProvider
import net.androidweekly.data.models.issues.Issue
import net.androidweekly.data.models.items.Item
import net.androidweekly.data.network.Resource
import net.androidweekly.data.network.tryResource
import net.androidweekly.data.repositories.issues.IssuesRepository

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
class LatestIssueViewModel(
    private val issuesRepository: IssuesRepository,
    private val coroutinesContextProvider: CoroutinesContextProvider
) : BaseViewModel(), LifecycleObserver {

    private val issuesResourceLiveData = MutableLiveData<Resource>()

    private val items: MutableList<Item> = mutableListOf()

    fun getIssues(): LiveData<Resource> {
        viewModelScope.launch(coroutinesContextProvider.io) {
            showProgress()

            val resource = tryResource { issuesRepository.getLatestIssue() }
            resource.element<Issue>()?.let { items.addAll(it.getItems()) }

            viewModelScope.launch(coroutinesContextProvider.main) {
                issuesResourceLiveData.value = resource
            }

            hideProgress()
        }
        return issuesResourceLiveData
    }

    private fun showProgress() {
        viewModelScope.launch(coroutinesContextProvider.main) {
            issuesResourceLiveData.value = Resource.Loading(show = true)
        }
    }

    private fun hideProgress() {
        viewModelScope.launch(coroutinesContextProvider.main) {
            issuesResourceLiveData.value = Resource.Loading(show = false)
        }
    }

    fun getItemsCount(): Int {
        return items.size
    }

    fun getItem(position: Int): Item {
        return items[position]
    }
}
