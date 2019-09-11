package com.saraelmoghazy

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.saraelmoghazy.base.R
import com.saraelmoghazy.base.baselayer.BaseActivity
import com.saraelmoghazy.base.searchpeople.ui.SearchPeopleFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override val currentToolBar: Toolbar
        get() = toolbar
    override val layout: View
        get() = mainLayout
    override val fragmentPlaceHolder: Int
        get() = R.id.fragmentContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        navigateToFragment(
            SearchPeopleFragment(), null, false,
            SearchPeopleFragment.TAG
        )
    }
}
