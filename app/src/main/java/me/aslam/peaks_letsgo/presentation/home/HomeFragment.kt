package me.aslam.peaks_letsgo.presentation.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.aslam.peaks_letsgo.R
import me.aslam.peaks_letsgo.core.base.BaseFragment
import me.aslam.peaks_letsgo.data.enities.TaskEntity
import me.aslam.peaks_letsgo.databinding.FragmentHomeBinding


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()
    override fun getBaseViewModel(): HomeViewModel = viewModel

    private val taskListAdapter by lazy {
        TaskListAdapter(::onItemClick)
    }

    private fun onItemClick(taskEntity: TaskEntity) {
    }

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun configureViews() {
        super.configureViews()

        binding?.apply {
            floatingButtonAdd.setOnClickListener {
                findNavController().navigate(R.id.add_task_fragment)
            }
        }


        binding?.recyclerviewTasks?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = taskListAdapter
        }
        viewModel.getTask()
        lifecycleScope.launch {
            viewModel.taskFlow.collectLatest {
                taskListAdapter.submitList(it)
            }
        }
    }
}



