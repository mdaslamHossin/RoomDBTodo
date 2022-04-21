package me.aslam.peaks_letsgo.data.remote.repository

import kotlinx.coroutines.flow.Flow

/**
 * Created by aslam on 21,April,2022
 */
interface UserRepository {

    suspend fun getNames(): Flow<MutableList<String>>
}
