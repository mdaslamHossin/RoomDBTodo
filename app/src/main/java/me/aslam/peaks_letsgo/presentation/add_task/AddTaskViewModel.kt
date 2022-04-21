package me.aslam.peaks_letsgo.presentation.add_task

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.aslam.peaks_letsgo.core.base.BaseViewModel
import me.aslam.peaks_letsgo.data.enities.TaskEntity
import me.aslam.peaks_letsgo.domain.use_case.AddTaskUseCase
import javax.inject.Inject

/**
 * Created by aslam on 21,April,2022
 */
@HiltViewModel
class AddTaskViewModel @Inject constructor(private val addTaskUseCase: AddTaskUseCase): BaseViewModel() {

    fun saveTask(taskEntity: TaskEntity) {
        viewModelScope.launch {
            addTaskUseCase.invoke(task = taskEntity)
        }
    }
}
