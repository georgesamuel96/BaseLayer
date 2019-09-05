package com.saraelmoghazy.base.baselayer;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Sara Elmoghazy
 */
public abstract class BaseAdapter<T, V extends ViewDataBinding>
        extends RecyclerView.Adapter<BaseViewHolder<T, V>> {

    @NonNull
    @Override
    public BaseViewHolder<T, V> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        V binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), getLayout(), parent, false);
        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<T, V> holder, int position) {
        final T item = getItemForPosition(position);
        holder.bind(item);
    }

    protected abstract T getItemForPosition(int position);

    protected abstract int getLayout();

}

