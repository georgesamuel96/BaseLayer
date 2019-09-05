package com.saraelmoghazy.base.ui

import androidx.databinding.ViewDataBinding
import com.saraelmoghazy.base.R
import com.saraelmoghazy.base.baselayer.BaseAdapter
import com.saraelmoghazy.base.model.ResultsItem

/**
 * Created by Sara Elmoghazy.
 */
class PeopleAdapter(resultItems: ArrayList<ResultsItem?>?) : BaseAdapter<ResultsItem, ViewDataBinding>() {
    private val resultItems: ArrayList<ResultsItem?>? = resultItems

    override fun getItemForPosition(position: Int): ResultsItem? {
        return resultItems?.get(position)
    }

    override fun getItemCount(): Int {
        return resultItems?.size ?: 0
    }

    override fun getLayout(): Int {
        return R.layout.partial_people_item
    }

    fun updateRV(items: List<ResultsItem?>?) {
        resultItems?.clear()
        if (items != null)
            resultItems?.addAll(items)
        notifyDataSetChanged()
    }
}