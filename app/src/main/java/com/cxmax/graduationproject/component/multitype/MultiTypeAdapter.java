package com.cxmax.graduationproject.component.multitype;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.cxmax.graduationproject.util.Preconditions;

import java.util.List;


/**
 * @describe : base adapter can support multi type items and simplify their generated codes,
 *             you need only care about the following abstract methods
 *
 * @usage : 1.extends this;
 *          2.override registerAllProvider() , updateData()
 * <p>
 * <p>
 * Created by caixi on 2017/3/27.
 */

public abstract class MultiTypeAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @NonNull
    protected Context context;
    @Nullable
    protected List<T> data;
    @NonNull
    protected MultiTypePool pool;

    public MultiTypeAdapter(@NonNull Context context, @Nullable List<T> data) {
        this.context = context;
        this.data = data;
        pool = new MultiTypePool();
        registerAllProvider(pool);
    }

    public void setData(@Nullable List<T> data) {
        this.data = data;
    }

    public void addData(@Nullable List<T> newItems) {
        this.data.addAll(newItems);
    }

    @Nullable
    public List<T> getData() {
        return data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return pool.onCreateViewHolder(parent , viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        pool.onBindViewHolder(data.get(position) , holder , position);
    }

    @Override
    public int getItemCount() {
        return Preconditions.isEmpty(data) ? 1 : data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (pool.getProviderCount() <= 0) {
            return super.getItemViewType(position);
        }
        return pool.getItemViewType(data.get(position) , position);
    }

    /**
     * register all of ItemProviders which this adapter will holds
     * @param pool
     */
    protected abstract void registerAllProvider(@NonNull MultiTypePool pool);

    /**
     * if use diffUtil , the concrete logic will be written in the child class
     * @param newItems
     */
    public abstract void updateData(List<T> newItems);
}
