package net.androidweekly.jobs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.androidweekly.data.repositories.jobs.JobsRepository
import javax.inject.Inject

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
class JobsViewModelFactory @Inject constructor(
    private val jobsRepository: JobsRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return JobsViewModel(jobsRepository) as T
    }
}
