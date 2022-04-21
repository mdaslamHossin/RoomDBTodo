package me.aslam.peaks_letsgo.core.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding
    abstract fun getViewBinding(): VB

    private var baseActivity: BaseActivity<VB>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            val activity: BaseActivity<VB> = context as BaseActivity<VB>
            baseActivity = activity
        }
    }

    val locationPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val values: Set<Boolean> = HashSet<Boolean>(permissions.values)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding()
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(savedInstanceState)
        lifecycleScope.launch { bindWithViewModel() }
    }

    open fun init(savedInstanceState: Bundle?) {
        configureViews()
        configureViews(savedInstanceState)


    }

    fun navigateTo(
        defaultDestination: NavDirections? = null,
        deepLink: String? = null,
        navOptions: NavOptions? = null
    ) = baseActivity?.navigateTo(defaultDestination, deepLink, navOptions)

    fun navigateTo(id: Int) {
        findNavController().navigate(id)
    }

    open fun configureViews() {}

    open fun configureViews(savedInstanceState: Bundle?) {}

    open fun getBaseViewModel(): BaseViewModel? {
        return null
    }


    open suspend fun bindWithViewModel() {
        getBaseViewModel()?.let { vModel ->
            vModel.progress.collect {
                when (it) {
                    is BaseViewModel.Progress.Show -> {
                        baseActivity?.showProgress()
                    }
                    is BaseViewModel.Progress.Hide -> {
                        baseActivity?.hideProgress()
                    }
                }
            }

        }

    }


}



