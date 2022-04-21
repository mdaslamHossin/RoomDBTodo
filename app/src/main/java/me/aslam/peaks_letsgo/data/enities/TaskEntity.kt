package me.aslam.peaks_letsgo.data.enities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Created by aslam on 21,April,2022
 */

@Entity(tableName = "tasks")
@Parcelize
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    var task: String? = null,
    var desc: String? = null,
    var finishBy: String? = null,
    var finished: Boolean? = false // Todo Update task
) : Parcelable

