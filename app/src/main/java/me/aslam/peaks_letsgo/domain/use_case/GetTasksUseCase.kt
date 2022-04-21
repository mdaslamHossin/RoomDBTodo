package me.aslam.peaks_letsgo.domain.use_case

import me.aslam.peaks_letsgo.data.db.repository.TaskRepository
import javax.inject.Inject

/**
 * Created by aslam on 22,April,2022
 */
class GetTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend operator fun invoke()= taskRepository.getTask()

}

