package me.aslam.peaks_letsgo.core.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.URLUtil
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.aslam.peaks_letsgo.R
import timber.log.Timber


abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var _binding: VB? = null
    val binding get() = _binding
    abstract fun getViewBinding(): VB
    private var title: String? = null


    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        setContentView(binding?.root)
        init(savedInstanceState)
        lifecycleScope.launch { bindWithViewModel() }
    }

    fun navigateTo(
        defaultDestination: NavDirections? = null,
        deepLink: String? = null,
        navOptions: NavOptions? = null
    ) {
        if (defaultDestination == null && deepLink == null) {
            Timber.d("navigateTo: No defaultDestinationId or deepLink provided")
            return
        }
        try {
            val controller = findNavController(R.id.nav_host_fragment)
            if (deepLink != null) {
                processDeepLink(this, controller, Uri.parse(deepLink), navOptions)
                return
            }
            defaultDestination?.let { controller.navigate(it, navOptions) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * This method will help to find app deeplink, if found it will safely navigate there
     * or other wise will try validated the deeplink is valid URL or not,
     * if valid then safely open the URL
     * @param context is context of the current activity
     * @param controller is app navigation controller from nav host.
     * @param deepLink will contain app deepLink or web link.
     */
    private fun processDeepLink(
        context: Context,
        controller: NavController,
        deepLink: Uri,
        navOptions: NavOptions? = null
    ) {
        if (controller.graph.hasDeepLink(deepLink)) {
            controller.navigate(deepLink, navOptions)
        } else if (URLUtil.isValidUrl(deepLink.toString())) {
            try {
                val intent = Intent(Intent.ACTION_VIEW, deepLink)
                context.startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    abstract fun getActivityName(): String

    open fun configureViews() {}

    open fun getBaseViewModel(): BaseViewModel? = null

    open fun init(savedInstanceState: Bundle?) {
        configureViews()
    }

    open suspend fun bindWithViewModel() {
        getBaseViewModel()?.let { vModel ->
            vModel.progress.collectLatest {
                when (it) {
                    is BaseViewModel.Progress.Show -> {
                        showProgress()
                    }
                    is BaseViewModel.Progress.Hide -> {
                        hideProgress()
                    }
                }
            }


        }
    }

    open fun hideProgress() {
    }

    open fun showProgress() {
    }
}


