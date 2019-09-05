package com.saraelmoghazy.base.baselayer

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.saraelmoghazy.base.R
import com.saraelmoghazy.base.ui.NoConnectionErrorView
import kotlinx.android.synthetic.main.base_layout.*
import kotlinx.android.synthetic.main.base_layout.view.*

/**
 * Created by Sara Elmoghazy
 */
abstract class BaseFragment<V : BaseViewModel> : Fragment() {

    private lateinit var viewModel: V
    private lateinit var oldView: View
    private var loadingIndicator: Dialog? = null
    private var noConnectionErrorView: NoConnectionErrorView? = null
    lateinit var rootView: View

    /**
     * @return layout resource id
     */
    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.base_layout, container, false)
        setupFragment()
        return rootView
    }

    private fun setupFragment() {
        setFragmentLayout()
        onViewInflated()
        getContentChildView()
        observeLoading()
        observeError()
    }

    private fun setFragmentLayout() {
        View.inflate(context, layoutId, rootView.contentContainer)
    }

    private fun getContentChildView() {
        if (rootView.parentLayout != null) {
            oldView = rootView.parentLayout.getChildAt(0)
        }
    }

    private fun switchTheView() {
        toggleErrorContainerView(oldView)
    }

    private fun tryAgain() {
        switchTheView()
        restart()
    }

    protected abstract fun getViewModel(): V

    private fun restart() {
        viewModel.restart()
    }


    /**
     * handle errors (network errors /API errors)
     *
     * @param error
     */
    private fun handleError(error: APIException) {
        if (error.errorType == ErrorType.HTTP)
            handleApiError(error)
        else
        showNoInternetConnectionDialog()
    }

    private fun handleApiError(error: APIException) {
        buildErrorDialog(error.message)

    }

    private fun showNoInternetConnectionDialog() {
        if (noConnectionErrorView == null)
            noConnectionErrorView =
                NoConnectionErrorView(this.context, View.OnClickListener { tryAgain() })
        addErrorView(noConnectionErrorView)
    }


    private fun buildErrorDialog(error: String?) {
        MaterialAlertDialogBuilder(context!!)
            .setMessage(error)
            .setPositiveButton(getString(R.string.confirm_dialog), null)
            .show()
    }


    private fun addErrorView(view: NoConnectionErrorView?) {
        if (view != null) {
            toggleErrorContainerView(view)
        }
    }

    private fun toggleErrorContainerView(view: View) {
        if (parentLayout != null) {
            if (view.parent != null) {
                (view.parent as ViewGroup).removeView(view)
            }
            parentLayout.removeAllViews()
            parentLayout.addView(view, 0)
        }
    }

    /**
     * show progress indicator to indicates that the process is in processing.
     */
    private fun showProgressLoadingIndicator() {
        if (loadingIndicator == null && isAdded) {
            loadingIndicator = Dialog(this.context!!)
            loadingIndicator?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            loadingIndicator?.setContentView(R.layout.partial_blocking_loading_indicator)
            loadingIndicator?.setCancelable(false)
            loadingIndicator?.window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
            loadingIndicator?.show()
        }
    }

    /**
     * Hide previously displayed progress indicator to indicates that the process is finished.
     */
    private fun hideProgressLoadingIndicator() {
        loadingIndicator?.dismiss()
        loadingIndicator = null
    }


    /**
     * observe error
     */
    private fun observeError() {
        viewModel.errorLiveData.observe(this, Observer { error ->
            if (error != null)
                handleError(error)
        })
    }

    /**
     * observe loading
     */
    private fun observeLoading() {
        viewModel.loadingIndicatorLiveData.observe(this, Observer { isLoading ->
            when (isLoading) {
                true -> showProgressLoadingIndicator()
                else -> hideProgressLoadingIndicator()
            }
        })
    }

    protected abstract fun onViewInflated()
}
