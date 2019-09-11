package com.saraelmoghazy.base.baselayer

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.saraelmoghazy.base.R
import kotlinx.android.synthetic.main.partial_no_connection.view.*

/**
 * Created by Sara Elmoghazy
 * No connection error view.
 */
class NoConnectionErrorView : ConstraintLayout {

    private var onClick: OnClickListener? = null

    constructor(
        context: Context?,
        onClickListener: OnClickListener
    ) : super(context) {
        this.onClick = onClickListener
        inflateErrorView()
    }

    private fun inflateErrorView() {
        View.inflate(context, R.layout.partial_no_connection, this)
        if (onClick != null)
            btn_retry.setOnClickListener(onClick)
    }
}
