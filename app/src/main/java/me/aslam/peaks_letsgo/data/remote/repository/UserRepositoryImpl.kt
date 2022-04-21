package me.aslam.peaks_letsgo.data.remote.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.aslam.peaks_letsgo.core.base.BaseRepository
import me.aslam.peaks_letsgo.data.remote.service.ApiService
import javax.inject.Inject

/**
 * Created by aslam on 21,April,2022
 */
class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): UserRepository, BaseRepository(){

    override suspend fun getNames(): Flow<MutableList<String>> {
        return flow {
            emit(mutableListOf())
        }
    }

}
