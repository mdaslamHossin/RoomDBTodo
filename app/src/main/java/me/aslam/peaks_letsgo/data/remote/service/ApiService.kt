package me.aslam.peaks_letsgo.data.remote.service

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("getNames")
    suspend fun getNames(): Response<MutableList<String>>

}

