package me.aslam.peaks_letsgo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.aslam.peaks_letsgo.data.db.dao.TaskDao
import me.aslam.peaks_letsgo.data.enities.TaskEntity

/**
 * Created by aslam on 22,April,2022
 */
@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class PeaksDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        fun create(context: Context): PeaksDatabase {
            val databaseBuilder =
                Room.databaseBuilder(context, PeaksDatabase::class.java, "peaks.db")
                    .fallbackToDestructiveMigration().allowMainThreadQueries()
            return databaseBuilder.build()
        }
    }
}
