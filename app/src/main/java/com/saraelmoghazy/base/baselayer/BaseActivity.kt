package com.saraelmoghazy.base.baselayer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import com.saraelmoghazy.base.R
import com.saraelmoghazy.base.util.ConnectivityUtil
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

abstract class BaseActivity : AppCompatActivity() {

    private var snackbar: Snackbar? = null
    val isNetworkAvailable: Subject<Boolean> = PublishSubject.create()
    private var isLaunched: Boolean = false
    abstract val fragmentPlaceHolder: Int
    private val networkReceiver = NetworkChangeReceiver()
    protected abstract val layout: View
    protected abstract val currentToolBar: Toolbar

    override fun onResume() {
        super.onResume()
        isLaunched = true
        registerReceiver(networkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(networkReceiver)
    }

    /**
     * navigate to next fragment
     *
     * @param nextFragment
     * @param previousFragmentTag
     * @param addToBackStack
     * @param fragmentTransition
     * @param currentFragmentTag
     */
    fun navigateToFragment(
        nextFragment: BaseFragment<*>, previousFragmentTag: String,
        addToBackStack: Boolean, fragmentTransition: Int, currentFragmentTag: String
    ) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setTransition(fragmentTransition)
        fragmentTransaction.replace(fragmentPlaceHolder, nextFragment, currentFragmentTag)
        if (addToBackStack)
            fragmentTransaction.addToBackStack(previousFragmentTag)

        fragmentTransaction.commit()
    }

    /**
     * Network change recovered listener , show status snack bar and expose to current fragment
     */
    private inner class NetworkChangeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val isConnected = ConnectivityUtil.isInternetAvailable(context)
            isNetworkAvailable.onNext(isConnected)
            if (isConnected) {
                if (isLaunched)
                    return
                else
                    buildConnectionAvailableMessage()
            } else
                buildNoConnectionMessage()
            snackbar!!.show()
            isLaunched = false
        }
    }

    /**
     * show connection is available snackbar
     */
    private fun buildNoConnectionMessage() {
        snackbar = Snackbar
            .make(layout, getString(R.string.no_connection_header), Snackbar.LENGTH_INDEFINITE)
    }

    /**
     * show connection is not available snackbar
     */
    private fun buildConnectionAvailableMessage() {
        snackbar = Snackbar
            .make(layout, getString(R.string.connection_return_header), Snackbar.LENGTH_SHORT)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    fun initToolbar() {
        setSupportActionBar(currentToolBar)
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                currentToolBar.setNavigationOnClickListener { onBackPressed() }
            }
        }
    }

}
