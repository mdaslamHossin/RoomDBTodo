package me.aslam.peaks_letsgo.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.aslam.peaks_letsgo.data.db.PeaksDatabase
import me.aslam.peaks_letsgo.data.db.dao.TaskDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): PeaksDatabase =
        PeaksDatabase.create(context)

    @Singleton
    @Provides
    fun provideTaskDao(peaksDatabase: PeaksDatabase): TaskDao = peaksDatabase.taskDao()
}




