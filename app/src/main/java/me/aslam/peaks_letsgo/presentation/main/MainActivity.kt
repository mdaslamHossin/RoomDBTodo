package me.aslam.peaks_letsgo.presentation.main


import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import me.aslam.peaks_letsgo.core.base.BaseActivity
import me.aslam.peaks_letsgo.core.base.BaseViewModel
import me.aslam.peaks_letsgo.databinding.ActivityMainBinding


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    private val viewModel: MainViewModel by viewModels()
    override fun getBaseViewModel(): BaseViewModel = viewModel
    override fun getActivityName(): String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


}



