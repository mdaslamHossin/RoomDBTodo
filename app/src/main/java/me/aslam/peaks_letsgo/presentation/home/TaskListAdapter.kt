package me.aslam.peaks_letsgo.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.aslam.peaks_letsgo.data.enities.TaskEntity
import me.aslam.peaks_letsgo.databinding.LayoutTaskItemBinding


/**
 * Created by aslam on 22,April,2022
 */

class TaskListAdapter constructor(
    private val onItemClick: (TaskEntity) -> Unit
) : ListAdapter<TaskEntity, TaskListAdapter.ItemViewHolder>(TaskDiff) {


    class ItemViewHolder private constructor(val binding: LayoutTaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TaskEntity) {

            binding.textTask.text = item.task
            binding.textDesc.text = item.desc
            binding.textStatus.text = if (item.finished == true) "Completed" else "In progress"
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val bindingElement =
                    LayoutTaskItemBinding.inflate(layoutInflater, parent, false)
                return ItemViewHolder(bindingElement)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onItemClick.invoke(getItem(position))
        }
    }


    object TaskDiff : DiffUtil.ItemCallback<TaskEntity>() {
        override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity) =
            oldItem == newItem
    }
}



