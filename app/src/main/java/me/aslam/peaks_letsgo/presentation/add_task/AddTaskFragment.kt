package me.aslam.peaks_letsgo.presentation.add_task

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import me.aslam.peaks_letsgo.core.base.BaseFragment
import me.aslam.peaks_letsgo.data.enities.TaskEntity
import me.aslam.peaks_letsgo.databinding.FragmentAddTaskBinding

/**
 * Created by aslam on 21,April,2022
 */

@AndroidEntryPoint
class AddTaskFragment : BaseFragment<FragmentAddTaskBinding>() {

    private val viewModel: AddTaskViewModel by viewModels()
    override fun getBaseViewModel(): AddTaskViewModel = viewModel


    override fun getViewBinding(): FragmentAddTaskBinding =
        FragmentAddTaskBinding.inflate(layoutInflater)

    override fun configureViews() {
        super.configureViews()
        binding?.apply {
            buttonSave.setOnClickListener {
                saveTask()
            }
        }
    }

    private fun saveTask(){
        binding?.apply {
            val task = editTextTask.text.toString()
            val desc= editTextDesc.text.toString()
            val finishBy= editFinishBy.text.toString()
            if (task.isEmpty()) {
                editTextTask.error = "Task required"
                editTextTask.requestFocus()
                return
            }

            if (desc.isEmpty()) {
                editTextDesc.error = "Desc required"
                editTextDesc.requestFocus()
                return
            }

            if (finishBy.isEmpty()) {
                editFinishBy.error = "Finish by required"
                editFinishBy.requestFocus()
                return
            }

            val taskEntity= TaskEntity(task = task, desc = desc, finishBy = finishBy)

            viewModel.saveTask(taskEntity)
            findNavController().navigateUp()

        }
    }
}
