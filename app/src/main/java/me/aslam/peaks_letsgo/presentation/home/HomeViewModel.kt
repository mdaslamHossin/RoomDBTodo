package me.aslam.peaks_letsgo.presentation.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.aslam.peaks_letsgo.core.base.BaseViewModel
import me.aslam.peaks_letsgo.data.enities.TaskEntity
import me.aslam.peaks_letsgo.domain.use_case.GetTasksUseCase
import me.aslam.peaks_letsgo.domain.use_case.UserNamesUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userNamesUseCase: UserNamesUseCase,
    private val getTasksUseCase: GetTasksUseCase
) : BaseViewModel() {

    private val _taskStateFlow: MutableStateFlow<List<TaskEntity>> =
        MutableStateFlow(listOf())
    val taskFlow = _taskStateFlow.asStateFlow()

    fun getTask(){
        viewModelScope.launch {
            getTasksUseCase.invoke().collectLatest {
                _taskStateFlow.value=it
            }
        }
    }

}


