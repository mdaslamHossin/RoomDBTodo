package me.aslam.peaks_letsgo.domain.use_case

import androidx.room.Index
import me.aslam.peaks_letsgo.data.db.repository.TaskRepository
import me.aslam.peaks_letsgo.data.enities.TaskEntity
import javax.inject.Inject

/**
 * Created by aslam on 22,April,2022
 */
class AddTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend operator fun invoke(task: TaskEntity){
        taskRepository.insert(task)
    }

}

