package me.aslam.peaks_letsgo.core.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.aslam.peaks_letsgo.data.remote.service.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val API_KEY_HEADER = "X-API-Key"
    private const val API_KEY_RESPONSE_FORMAT = "accept"
    private const val API_KEY_LANGUAGE = "Accept-Language"

    /**
     * provide common Gson Builder
     */
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson = GsonBuilder().create()

    /**
     * provide common HttpLoggingInterceptor
     * @param interceptor:HttpLoggingInterceptor
     * @param level: HttpLoggingInterceptor.Level.BODY
     */
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }


    /**
     * provide common Common API header Auth interceptor
     * @param HEADER_KEY will be used for authentication
     */
    @Singleton
    @Provides
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor {
            it.proceed(it.request().newBuilder().build())
        }
    }

    /**
     * provide ACME API okhttp client
     * @param httpLoggingInterceptor: common interceptor injected here
     * @param headerInterceptor: common header authenticator interceptor injected here
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(httpLoggingInterceptor)
        httpClient.addInterceptor(headerInterceptor)
        return httpClient.build()
    }

    /**
     * provide ACME API Retrofit builder
     * @param gson: Json data parser from server side
     * @param okHttpClient: ACME API qualified client injected here
     */

    @Singleton
    @Provides
    fun provideRetrofit(
        gson: Gson, okHttpClient: OkHttpClient,
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://www.peaks.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService = retrofit.create(ApiService::class.java)

}



