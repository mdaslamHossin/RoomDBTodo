package me.aslam.peaks_letsgo.data.db.repository

import kotlinx.coroutines.flow.Flow
import me.aslam.peaks_letsgo.data.db.dao.TaskDao
import me.aslam.peaks_letsgo.data.enities.TaskEntity
import okhttp3.internal.concurrent.Task
import javax.inject.Inject

/**
 * Created by aslam on 22,April,2022
 */
class TaskRepositoryImpl @Inject constructor(private val taskDao: TaskDao) : TaskRepository{

   override suspend  fun getTask(): Flow<List<TaskEntity>> {
        return  taskDao.getTasks()
    }

    override suspend fun insert(task: TaskEntity) {
       taskDao.insertTask(task)

    }


}


