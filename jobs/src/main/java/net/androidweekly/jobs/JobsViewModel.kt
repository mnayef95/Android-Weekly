package net.androidweekly.jobs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.androidweekly.core.BaseViewModel
import net.androidweekly.data.models.jobs.Job
import net.androidweekly.data.models.jobs.JobsWrapper
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

    private val jobs: MutableList<Job> = mutableListOf()

    fun getJobs(): LiveData<Resource> {
        if (jobs.size == 0) {
            viewModelScope.launch(Dispatchers.IO) {
                jobsResourceLiveData.postValue(Resource.Loading(show = true))

                val resource = tryResource { jobsRepository.getAllJobs() }
                this@JobsViewModel.jobs += resource.element<JobsWrapper>()?.jobs ?: listOf()

                jobsResourceLiveData.postValue(resource.ignoreElement())
            }
        }
        return jobsResourceLiveData
    }

    fun getJobsCount(): Int {
        return jobs.size
    }

    fun getJob(position: Int): Job {
        return jobs[position]
    }
}
