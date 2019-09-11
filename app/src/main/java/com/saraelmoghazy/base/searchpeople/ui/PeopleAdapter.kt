package com.saraelmoghazy.base.searchpeople.ui

import androidx.databinding.ViewDataBinding
import com.saraelmoghazy.base.R
import com.saraelmoghazy.base.baselayer.BaseAdapter
import com.saraelmoghazy.base.baselayer.BaseViewHolder
import com.saraelmoghazy.base.searchpeople.model.ResultsItem
import io.reactivex.subjects.Subject
import kotlinx.android.synthetic.main.partial_people_item.view.*


/**
 * Created by Sara Elmoghazy.
 */
class PeopleAdapter(
    private val resultItems: ArrayList<ResultsItem>,
    private val onItemClickListener: Subject<ResultsItem>
) :

    BaseAdapter<ResultsItem, ViewDataBinding>() {
    override val layout: Int
        get() = R.layout.partial_people_item

    override fun getItemForPosition(position: Int): ResultsItem {
        return resultItems[position]
    }

    override fun getItemCount(): Int {
        return resultItems.size
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ResultsItem, ViewDataBinding>,
        position: Int
    ) {
        super.onBindViewHolder(holder, position)
        val item = getItemForPosition(position)
        holder.binding.root.itemLayout.setOnClickListener { onItemClickListener.onNext(item) }
    }

    fun updateRV(items: List<ResultsItem>) {
        resultItems.clear()
        resultItems.addAll(items)
        notifyDataSetChanged()
    }
}