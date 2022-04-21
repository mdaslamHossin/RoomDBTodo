package me.aslam.peaks_letsgo.data.db.repository

import kotlinx.coroutines.flow.Flow
import me.aslam.peaks_letsgo.data.enities.TaskEntity

/**
 * Created by aslam on 22,April,2022
 */

interface TaskRepository {


    suspend fun getTask(): Flow<List<TaskEntity>>

    suspend fun insert(task: TaskEntity)

}


