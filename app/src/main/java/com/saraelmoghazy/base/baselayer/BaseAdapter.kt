package com.saraelmoghazy.base.baselayer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Sara Elmoghazy
 */
abstract class BaseAdapter<T, V : ViewDataBinding> : RecyclerView.Adapter<BaseViewHolder<T, V>>() {

    protected abstract val layout: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T, V> {
        val binding = DataBindingUtil.inflate<V>(
            LayoutInflater.from(parent.context), layout, parent, false
        )
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T, V>, position: Int) {
        val item = getItemForPosition(position)
        holder.bind(item)
    }

    protected abstract fun getItemForPosition(position: Int): T

}

