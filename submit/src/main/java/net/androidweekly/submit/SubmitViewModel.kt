package net.androidweekly.submit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.androidweekly.core.BaseViewModel
import net.androidweekly.data.models.issues.AuthenticityTokens
import net.androidweekly.data.network.Resource
import net.androidweekly.data.network.tryResource
import net.androidweekly.data.repositories.issues.IssuesRepository

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
class SubmitViewModel(
    private val issuesRepository: IssuesRepository
) : BaseViewModel() {

    private val _submitLiveData = MutableLiveData<Resource>()
    val submitLiveData: LiveData<Resource> = _submitLiveData

    fun submitLink(link: String?, checkedChipId: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            showProgress()
            val resource = tryResource { issuesRepository.getAuthenticityTokens() }
            if (resource is Resource.Success<*>) {
                val submitResource = tryResource { startSubmitLink(link, checkedChipId, resource) }
                viewModelScope.launch(Dispatchers.Main) {
                    _submitLiveData.value = submitResource
                }
            } else {
                viewModelScope.launch(Dispatchers.Main) {
                    _submitLiveData.value = resource
                }
            }
            hideProgress()
        }
    }

    private suspend fun startSubmitLink(link: String?, checkedChipId: Int?, resource: Resource.Success<*>) {
        val data = resource.element<AuthenticityTokens>()
        if (checkedChipId == R.id.chip_activity_submit_link) {
            issuesRepository.submitLink(link, data?.linkToken)
        } else {
            issuesRepository.submitConference(link, data?.conferenceToken)
        }
    }

    private fun showProgress() {
        viewModelScope.launch(Dispatchers.Main) {
            _submitLiveData.value = Resource.Loading(show = true)
        }
    }

    private fun hideProgress() {
        viewModelScope.launch(Dispatchers.Main) {
            _submitLiveData.value = Resource.Loading(show = false)
        }
    }
}
