package com.saraelmoghazy.base.baselayer
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Sara Elmoghazy
 */
class BaseViewHolder<T, V : ViewDataBinding>(private val binding: V) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T) {
        binding.setVariable(BR.obj, item)
        binding.executePendingBindings()
    }
}
