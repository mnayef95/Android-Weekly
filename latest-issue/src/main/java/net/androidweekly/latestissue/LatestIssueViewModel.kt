package net.androidweekly.latestissue

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.androidweekly.core.BaseViewModel
import net.androidweekly.data.models.issues.Issue
import net.androidweekly.data.models.items.IssueTitle
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
class LatestIssueViewModel(private val issuesRepository: IssuesRepository) : BaseViewModel() {

    private val _remoteIssuesFailedLiveData = MutableLiveData<Int>()
    private val _issuesResourceLiveData = MutableLiveData<Resource>()

    val issuesResourceLiveData: LiveData<Resource> = _issuesResourceLiveData
    val remoteIssuesFailedLiveData: LiveData<Int> = _remoteIssuesFailedLiveData

    private var items: List<Item>? = null

    fun getIssues() {
        items = null
        viewModelScope.launch(Dispatchers.IO) {
            showProgress()
            val resource = tryResource { issuesRepository.getRemoteLatestIssue() }
            items = resource.element<Issue>()?.getItems()

            handleLocalJobs(resource)
            handleResult(resource)
        }
    }

    private fun showProgress() {
        viewModelScope.launch(Dispatchers.Main) {
            _issuesResourceLiveData.value = Resource.Loading(show = true)
        }
    }

    private suspend fun handleLocalJobs(resource: Resource) {
        if (resource is Resource.Error) {
            items = issuesRepository.getLocalLatestIssues()?.getItems()
        }
    }

    private fun handleResult(resource: Resource) {
        viewModelScope.launch(Dispatchers.Main) {
            if (items?.isNotEmpty() == true && resource is Resource.Error) {
                _remoteIssuesFailedLiveData.value = resource.error.resourceMessage
            } else {
                _issuesResourceLiveData.value = resource.ignoreElement()
            }
            hideProgress()
        }
    }

    private fun hideProgress() {
        _issuesResourceLiveData.value = Resource.Loading(show = false)
    }

    fun getItemsCount(): Int {
        return items?.size ?: 0
    }

    fun getItem(position: Int): Item? {
        return items?.get(position)
    }

    fun getClickedSectionItems(position: Int): Array<Item>? {
        val items = this.items?.subList(position + 1, this.items?.size ?: 0)
        var nextTitleIndex = items?.indexOfFirst { it is IssueTitle } ?: -1
        if (nextTitleIndex == -1) {
            nextTitleIndex = items?.size ?: 0
        }

        return items?.subList(0, nextTitleIndex)?.toTypedArray()
    }

    fun setItems(items: Array<out Parcelable>?) {
        this.items = (items as? Array<Item>?)?.toList()
        viewModelScope.launch(Dispatchers.Main) {
            _issuesResourceLiveData.value = Resource.Success(items).ignoreElement()
            _issuesResourceLiveData.value = Resource.Loading(show = false)
        }
    }
}
