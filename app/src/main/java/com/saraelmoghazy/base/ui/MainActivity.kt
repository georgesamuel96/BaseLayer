package com.saraelmoghazy.base.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.saraelmoghazy.base.R
import com.saraelmoghazy.base.baselayer.BaseFragment
import com.saraelmoghazy.base.viewmodel.StarWarViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToFragment(SearchPeopleFragment(), "", true, 1, "")
    }

    fun navigateToFragment(
        nextFragment: BaseFragment<StarWarViewModel>, previousFragmentTag: String,
        addToBackStack: Boolean, fragmentTransition: Int, currentFragmentTag: String) {
        supportFragmentManager.beginTransaction().apply {
            setTransition(fragmentTransition)
            replace(R.id.fragment_container, nextFragment, currentFragmentTag)
            if (addToBackStack)
                addToBackStack(previousFragmentTag)
            commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("destory","destory")
    }
}
