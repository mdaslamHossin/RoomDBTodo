package me.aslam.peaks_letsgo.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {


    private val progressChannel = Channel<Progress>(Channel.BUFFERED)
    private var progressCount = 0
    val progress: Flow<Progress>
        get() = progressChannel.receiveAsFlow()

    companion object {
        private const val SSID_RESPONSE_SLUG = "testssid"
    }

    fun showProgress() {
        viewModelScope.launch {
            if (++progressCount == 1) {
                progressChannel.send(Progress.Show)

            }
        }
    }

    fun hideProgress() {
        viewModelScope.launch {
            if (--progressCount == 0) {
                progressChannel.send(Progress.Hide)
            }

            if (progressCount < 0) {
                progressCount = 0
            }
        }
    }


    override fun onCleared() {
        Timber.d("onCleared() called")
        onClear()
        super.onCleared()
    }

    open fun onClear() {}


    sealed class Progress {
        object Show : Progress()
        object Hide : Progress()
    }

}
