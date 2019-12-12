package net.androidweekly.pastissue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.androidweekly.core.BaseViewModel
import net.androidweekly.data.models.issues.Issue
import net.androidweekly.data.network.Resource
import net.androidweekly.data.network.tryResource
import net.androidweekly.data.repositories.issues.IssuesRepository

/**
 * Created by Furkan on 2019-12-09
 */

class PastIssueViewModel(private val issuesRepository: IssuesRepository) : BaseViewModel() {

    private val _remoteIssuesFailedLiveData = MutableLiveData<Int>()
    private val _issuesResourceLiveData = MutableLiveData<Resource>()

    val issuesResourceLiveData: LiveData<Resource> = _issuesResourceLiveData
    val remoteIssuesFailedLiveData: LiveData<Int> = _remoteIssuesFailedLiveData

    private var items: List<Issue>? = null

    fun getPastIssues() {
        items = null
        viewModelScope.launch(Dispatchers.IO) {
            showProgress()
            val resource = tryResource { issuesRepository.getRemotePastIssues() }
            val items = resource.element<List<Issue>>()
            this@PastIssueViewModel.items = items?.subList(1, items.size)

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
            items = issuesRepository.getLocalPastIssues()
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

    fun getItem(position: Int): Issue? {
        return items?.get(position)
    }
}
