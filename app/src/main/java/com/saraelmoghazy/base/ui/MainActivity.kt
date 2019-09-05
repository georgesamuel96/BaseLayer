package com.saraelmoghazy.base.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.saraelmoghazy.base.R
import com.saraelmoghazy.base.baselayer.BaseActivity
import com.saraelmoghazy.base.baselayer.BaseFragment
import com.saraelmoghazy.base.viewmodel.StarWarViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    override val currentToolBar: Toolbar
        get() = toolbar
    override val layout: View
        get() = mainLayout
    override val fragmentPlaceHolder: Int
        get() = R.id.fragmentContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToFragment(SearchPeopleFragment(), "", true, 1, "")
    }
}
