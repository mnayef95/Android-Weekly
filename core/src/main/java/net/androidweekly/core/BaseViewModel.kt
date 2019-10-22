package net.androidweekly.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
abstract class BaseViewModel : ViewModel() {

    private var progressLiveData: MutableLiveData<Boolean>? = MutableLiveData()

    fun showProgress() {
        progressLiveData?.value = true
    }

    fun hideProgress() {
        progressLiveData?.value = false
    }

    override fun onCleared() {
        super.onCleared()
        progressLiveData = null
    }
}
