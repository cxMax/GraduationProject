package com.cxmax.graduationproject.component.multitype;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @describe : 1.manage the child {@link AbsItemProvider};
 *             2.provides the same methods to invoke method in {@link AbsItemProvider}
 *             to hook {@link android.support.v7.widget.RecyclerView.Adapter}'s lifecycle.
 * @usage :
 * <p>
 * <p>
 * Created by caixi on 2017/3/26.
 */

public class MultiTypePool<T, VH extends RecyclerView.ViewHolder> implements TypePool<T> {
    private static final int MAX_PROVIDER_VIEW_TYPE = Integer.MAX_VALUE - 1;
    @NonNull
    protected SparseArrayCompat<AbsItemProvider> providers = new SparseArrayCompat<>();

    @Override
    @Deprecated
    public void add(@NonNull AbsItemProvider provider) {
        int viewType = providers.size();
        if (providers.get(viewType) != null) {
            viewType++;
            if (viewType == MAX_PROVIDER_VIEW_TYPE) {
                throw new IllegalArgumentException("there is no more free and unused view type to add");
            }
        }
        add(viewType, false, provider);
    }

    @Override
    public void add(int viewType, @NonNull AbsItemProvider provider) {
        add(viewType, false, provider);
    }

    @Override
    public void add(int viewType, boolean allowReplace, @NonNull AbsItemProvider provider) {
        if (viewType == MAX_PROVIDER_VIEW_TYPE) {
            throw new IllegalArgumentException("there is no more free and unused view type to add");
        }
        if (!allowReplace && providers.get(viewType) != null) {
            throw new IllegalArgumentException("An AbsItemProvider was already registered ");
        }
        providers.put(viewType, provider);
    }

    @Override
    public void remove(@NonNull AbsItemProvider provider) {
        int index = providers.indexOfValue(provider);
        if (index >= 0) {
            providers.removeAt(index);
        }
    }

    @Override
    public void remove(int viewType) {
        int index = providers.indexOfKey(viewType);
        if (index >= 0) {
            providers.remove(viewType);
        }
    }

    @Override
    public int getItemViewType(T t, int position) {
        int delegatesCount = providers.size();
        for (int i = 0; i < delegatesCount; i++) {
            AbsItemProvider provider = providers.valueAt(i);
            if (provider.isForViewType(t, position)) {
                return providers.keyAt(i);
            }
        }
        throw new IllegalArgumentException("No AbsItemProvider added that matches position =" + position);
    }

    @Override
    public int getItemViewType(AbsItemProvider provider) {
        return providers.indexOfValue(provider);
    }

    @Override
    public AbsItemProvider getItemViewProvider(T t, int position) {
        int delegatesCount = providers.size();
        for (int i = 0; i < delegatesCount; i++) {
            AbsItemProvider provider = providers.valueAt(i);
            if (provider.isForViewType(t, position)) {
                return providers.valueAt(i);
            }
        }
        throw new IllegalArgumentException(
                "No AbsItemProvider added that matches position =" + position);
    }

    @Override
    public AbsItemProvider getItemViewProvider(int viewType) {
        AbsItemProvider provider = providers.get(viewType);
        if (provider == null) {
            throw new IllegalArgumentException("No AbsItemProvider added that matches viewType =" + viewType);
        }
        return provider;
    }

    public int getProviderCount() {
        return providers.size();
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AbsItemProvider provider = getItemViewProvider(viewType);
        assertProviderNotNull(provider);
        return provider.onCreateViewHolder(parent);
    }

    public void onBindViewHolder(@NonNull T t, @NonNull VH viewHolder, int position) {
        AbsItemProvider provider = getItemViewProvider(viewHolder.getItemViewType());
        assertProviderNotNull(provider);
        provider.onBindViewHolder(t, position, viewHolder);
    }

    public void onViewRecycled(@NonNull VH viewHolder) {
        AbsItemProvider provider = getItemViewProvider(viewHolder.getItemViewType());
        assertProviderNotNull(provider);
        provider.onViewRecycled(viewHolder);
    }

    public boolean onFailedToRecycleView(@NonNull VH viewHolder) {
        AbsItemProvider provider = getItemViewProvider(viewHolder.getItemViewType());
        assertProviderNotNull(provider);
        return provider.onFailedToRecycleView(viewHolder);
    }

    public void onViewAttachedToWindow(@NonNull VH viewHolder) {
        AbsItemProvider provider = getItemViewProvider(viewHolder.getItemViewType());
        assertProviderNotNull(provider);
        provider.onViewAttachedToWindow(viewHolder);
    }

    public void onViewDetachedFromWindow(@NonNull VH viewHolder) {
        AbsItemProvider provider = getItemViewProvider(viewHolder.getItemViewType());
        assertProviderNotNull(provider);
        provider.onViewDetachedFromWindow(viewHolder);
    }

    private void assertProviderNotNull(@Nullable AbsItemProvider provider) throws NullPointerException {
        if (provider == null) {
            throw new NullPointerException("this viewType AbsItemProvider was not registered");
        }
    }
}
