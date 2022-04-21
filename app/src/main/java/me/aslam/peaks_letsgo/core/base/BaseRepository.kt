package me.aslam.peaks_letsgo.core.base

import com.google.gson.stream.MalformedJsonException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import java.io.FileNotFoundException
import java.io.IOException
import java.net.SocketTimeoutException

abstract class BaseRepository {

    protected suspend inline fun <reified T> safeApiCall(
        crossinline processApiCall: suspend () -> Response<T>
    ) = flow {
        try {
            val response = processApiCall.invoke()
            if (response.isSuccessful && response.body() != null) {
                emit(null)
            } else {
                emit(
                   "Error text"
                )
            }
        } catch (e: Exception) {
            emit("Network Exception")
        }
    }.flowOn(Dispatchers.IO)

    fun getCustomErrorMessage(error: Exception): String {
        return when (error) {
            is SocketTimeoutException -> "Oh! We couldn't capture your request in time. Please try again."
            is MalformedJsonException -> "Oh! We hit an error. Try again later."
            is IOException -> "Oh! You are not connected to a wifi or cellular data network. Please connect and try again"
            is FileNotFoundException -> "Oh! No such file or directory"
            is HttpException -> error.message()
            else -> "Something Went Wrong!"
        }
    }


}


