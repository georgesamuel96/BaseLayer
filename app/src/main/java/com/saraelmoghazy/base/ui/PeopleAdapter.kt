package com.saraelmoghazy.base.ui

import androidx.databinding.ViewDataBinding
import com.saraelmoghazy.base.R
import com.saraelmoghazy.base.baselayer.BaseAdapter
import com.saraelmoghazy.base.model.ResultsItem

/**
 * Created by Sara Elmoghazy.
 */
class PeopleAdapter(private val resultItems: ArrayList<ResultsItem>) :
    BaseAdapter<ResultsItem, ViewDataBinding>() {
    override val layout: Int
        get() = R.layout.partial_people_item

    override fun getItemForPosition(position: Int): ResultsItem {
        return resultItems[position]
    }

    override fun getItemCount(): Int {
        return resultItems.size
    }

    fun updateRV(items: List<ResultsItem>) {
        resultItems.clear()
        resultItems.addAll(items)
        notifyDataSetChanged()
    }
}