package net.androidweekly.jobs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.androidweekly.core.BaseViewModel
import net.androidweekly.data.models.jobs.Job
import net.androidweekly.data.network.Resource
import net.androidweekly.data.network.tryResource
import net.androidweekly.data.repositories.jobs.JobsRepository

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
class JobsViewModel(private val jobsRepository: JobsRepository) : BaseViewModel() {

    private val jobsResourceLiveData = MutableLiveData<Resource>()
    private val _remoteJobsFailedLiveData = MutableLiveData<Int>()
    val remoteJobsFailedLiveData: LiveData<Int> = _remoteJobsFailedLiveData

    private var jobs: List<Job>? = null

    fun getJobs(): LiveData<Resource> {
        jobs = null
        viewModelScope.launch(Dispatchers.IO) {
            showProgress()
            val resource = tryResource { jobsRepository.getRemoteJobs() }
            jobs = resource.element()

            handleLocalJobs(resource)
            handleResult(resource)
        }
        return jobsResourceLiveData
    }

    private fun showProgress() {
        viewModelScope.launch(Dispatchers.Main) {
            jobsResourceLiveData.value = Resource.Loading(show = true)
        }
    }

    private suspend fun handleLocalJobs(resource: Resource) {
        if (resource is Resource.Error) {
            jobs = jobsRepository.getLocalJobs()
        }
    }

    private fun handleResult(resource: Resource) {
        viewModelScope.launch(Dispatchers.Main) {
            if (jobs?.isNotEmpty() == true && resource is Resource.Error) {
                _remoteJobsFailedLiveData.value = resource.error.resourceMessage
            } else {
                jobsResourceLiveData.value = resource.ignoreElement()
            }
            hideProgress()
        }
    }

    private fun hideProgress() {
        jobsResourceLiveData.value = Resource.Loading(show = false)
    }

    fun getJobsCount(): Int {
        return jobs?.size ?: 0
    }

    fun getJob(position: Int): Job? {
        return jobs?.get(position)
    }
}
