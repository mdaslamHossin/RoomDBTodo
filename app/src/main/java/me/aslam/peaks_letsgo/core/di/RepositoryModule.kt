package me.aslam.peaks_letsgo.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.aslam.peaks_letsgo.data.db.repository.TaskRepository
import me.aslam.peaks_letsgo.data.db.repository.TaskRepositoryImpl
import me.aslam.peaks_letsgo.data.remote.repository.UserRepository
import me.aslam.peaks_letsgo.data.remote.repository.UserRepositoryImpl

/**
 * Created by aslam on 21,April,2022
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository


    @Binds
    abstract fun provideTaskRepository(taskRepositoryImpl: TaskRepositoryImpl): TaskRepository

}

