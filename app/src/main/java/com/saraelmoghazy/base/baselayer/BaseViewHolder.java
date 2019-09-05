package com.saraelmoghazy.base.baselayer;

import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Sara Elmoghazy
 */
public class BaseViewHolder<T, V extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private final V binding;

    public BaseViewHolder(V binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(T item) {
        binding.setVariable(BR.obj, item);
        binding.executePendingBindings();
    }

    public V getBinding() {
        return binding;
    }
}
